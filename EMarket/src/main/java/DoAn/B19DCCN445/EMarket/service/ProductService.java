package DoAn.B19DCCN445.EMarket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.jpa.QueryHints;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.ProductDTO;
import DoAn.B19DCCN445.EMarket.dto.Product_reviewDTO;
import DoAn.B19DCCN445.EMarket.exception.ProductNotFoundException;
import DoAn.B19DCCN445.EMarket.exception.StorageException;
import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Order_detail;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Product_image;
import DoAn.B19DCCN445.EMarket.model.Product_review;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.repository.OrderDetailRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductImageRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductReviewRepository;
import jakarta.persistence.EntityManager;

@Service
public class ProductService {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ProductRepository repository;
	@Autowired
	private ProductImageRepository imageRepository;
	@Autowired
	FilesStorageService storageService;
	@Autowired
	ProductReviewRepository productReviewRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;

//	private Product findProduct(Long id) {
//		// TODO Auto-generated method stub
//		Product p = repository.findProduct(id);
//		p= entityManager.createQuery("""
//			    select distinct p
//			    from Product p
//			    left join fetch p.product_reviews
//			    where p in :product""", Product.class)
//			.setParameter("product", p)
//			.getSingleResult();
//		return p;
//	}
	public ProductDTO getProduct(Long id) throws ProductNotFoundException {
		Product p = repository.findProduct(id);

		if (p == null)
			throw new ProductNotFoundException("Product is not found");
//		List<Product_image> images= imageRepository.findByProductId(id);
//		System.out.println(images.get(0).getId());
//		p.setProduct_images(images);
		ProductDTO pdto = new ProductDTO();
		BeanUtils.copyProperties(p, pdto);
		pdto.setStore(p.getStore());
		List<Product_review> product_reviews = new ArrayList<>(p.getProduct_reviews());
//		List<Product_reviewDTO> product_reviewDTOs=new ArrayList<>();
		Integer reviews = product_reviews.size();
		Double rating;
		rating = 0.0;

		for (Product_review review : product_reviews) {
			rating += (double) (review.getRating());
//			Product_reviewDTO product_reviewDTO=new Product_reviewDTO();
//			BeanUtils.copyProperties(review, product_reviewDTO);
//			product_reviewDTOs.add(product_reviewDTO);
		}
		if (reviews > 0) {

			rating /= reviews;
		} else {
			rating = null;
		}
		pdto.setProduct_reviews(product_reviews);
		pdto.setRating(rating);
		pdto.setReviews(reviews);
		return pdto;
	}

	public ApiResponse saveProduct(Product product) {
//		Product product=new Product();
//		BeanUtils.copyProperties(productDto, product);
		product.setProduct_images(null);
		repository.save(product);
		return ApiResponse.builder().message("save Product successfully!").success(true).build();
	}

	public ApiResponse saveProductImages(MultipartFile[] files, String product_name) {
		Product product = repository.findByProduct_name(product_name);
		imageRepository.deleteByProduct(product.getId());
		product.setProduct_images(filesProduct(files, product));
		repository.save(product);
		return ApiResponse.builder().message("save Product successfully!").success(true).build();
	}

	public ArrayList<Product_image> filesProduct(MultipartFile[] files, Product product) {
		ArrayList<Product_image> images = new ArrayList<>();
		Arrays.asList(files).stream().forEach(file -> {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			Product_image image = Product_image.builder().image(storageService.getStoredFilename(file, uuString))
					.product(product).build();
			try {
				storageService.store(file, image.getImage());
			} catch (StorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			images.add(image);
		});
		return images;
	}

	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		List<ProductDTO> products = repository.findAllProduct().stream().map((product) -> {
//			List<Product_image> list=new ArrayList<>(product.getProduct_images());
//			System.out.println(list.get(0).getImage());
			ProductDTO pdto = new ProductDTO();
			BeanUtils.copyProperties(product, pdto);
			pdto.setStore(product.getStore());
			int quantitySold = 0;
			List<Order_detail> order_details = orderDetailRepository.findByProduct(product);
			for (Order_detail order_detail : order_details) {
				quantitySold += order_detail.getQuantity();
			}
			List<Product_review> product_reviews = new ArrayList<>(product.getProduct_reviews());
			Integer reviews = product_reviews.size();
			Double rating;
			rating = 0.0;

			for (Product_review review : product_reviews) {
				rating += (double) (review.getRating());
			}
			if (reviews > 0) {

				rating /= reviews;
			} else {
				rating = null;
			}
			pdto.setRating(rating);
			pdto.setReviews(reviews);
			pdto.setQuantitySold(quantitySold);
			pdto.setProduct_reviews(null);
			return pdto;
		}).collect(Collectors.toList());

		return products;
	}

	public List<ProductDTO> getProductsLikeName(String name) {
		// TODO Auto-generated method stub
		List<ProductDTO> products = repository.findByLikeName(name).stream().map((product) -> {
			ProductDTO pdto = new ProductDTO();
			BeanUtils.copyProperties(product, pdto);
			return pdto;
		}).collect(Collectors.toList());

		return products;
	}

	public ApiResponse getDeleteProduct(Long id) {
		repository.deleteById(id);
		return ApiResponse.builder().message("delete Product successfully!").success(true).build();
	}

	public List<ProductDTO> getAllProductsStore(Store store) {
		List<ProductDTO> products = repository.findAllProductsStore(store.getId()).stream().map((product) -> {
			ProductDTO pdto = new ProductDTO();
			BeanUtils.copyProperties(product, pdto);
			pdto.setStore(product.getStore());
			int quantitySold = 0;
			List<Order_detail> order_details = orderDetailRepository.findByProduct(product);
			for (Order_detail order_detail : order_details) {
				quantitySold += order_detail.getQuantity();
			}
			List<Product_review> product_reviews = new ArrayList<>(product.getProduct_reviews());
			Integer reviews = product_reviews.size();
			Double rating;
			rating = 0.0;

			for (Product_review review : product_reviews) {
				rating += (double) (review.getRating());
			}
			if (reviews > 0) {

				rating /= reviews;
			} else {
				rating = null;
			}
			pdto.setRating(rating);
			pdto.setReviews(reviews);
			pdto.setQuantitySold(quantitySold);
			pdto.setProduct_reviews(null);
			return pdto;
		}).collect(Collectors.toList());

		return products;
	}

	public ApiResponse saveReivew(Product_reviewDTO dto) {
//		System.out.println(dto);
		Product_review product_review = new Product_review();
		BeanUtils.copyProperties(dto, product_review);
		productReviewRepository.save(product_review);
		Order_detail order_detail = orderDetailRepository.findById(dto.getOrder_detail().getId()).get();
		order_detail.setIsReviewed(true);
		orderDetailRepository.save(order_detail);
		return ApiResponse.builder().message("save Review successfully!").success(true).build();
	}

}
