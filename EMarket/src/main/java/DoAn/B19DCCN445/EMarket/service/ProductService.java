package DoAn.B19DCCN445.EMarket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.ProductDTO;
import DoAn.B19DCCN445.EMarket.exception.ProductNotFoundException;
import DoAn.B19DCCN445.EMarket.exception.StorageException;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Product_image;
import DoAn.B19DCCN445.EMarket.repository.ProductImageRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private ProductImageRepository imageRepository;
	@Autowired
	FilesStorageService storageService;

	public ProductDTO getProduct(Long id) throws ProductNotFoundException {
		Product p = repository.findProduct(id);
		
		if (p == null)
			throw new ProductNotFoundException("Product is not found");
//		List<Product_image> images= imageRepository.findByProductId(id);
//		System.out.println(images.get(0).getId());
//		p.setProduct_images(images);
		ProductDTO pdto=new ProductDTO();
		BeanUtils.copyProperties(p, pdto);
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
		Product product= repository.findByProduct_name(product_name);
		imageRepository.deleteByProduct(product.getId());
		product.setProduct_images(filesProduct(files,product));
		repository.save(product);
		return ApiResponse.builder().message("save Product successfully!").success(true).build();
	}
	
	public ArrayList<Product_image> filesProduct(MultipartFile[] files, Product product){
		ArrayList<Product_image> images=new ArrayList<>();
		Arrays.asList(files).stream().forEach(file -> {
			UUID uuid=UUID.randomUUID();
			String uuString=uuid.toString();
			Product_image image=Product_image.builder().image(storageService.getStoredFilename(file, uuString)).product(product).build();
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
		List<ProductDTO> products=repository.findAllProduct().stream().map((product)->{
			List<Product_image> list=new ArrayList<>(product.getProduct_images());
			System.out.println(list.get(0).getImage());
//			System.out.println(product.getProduct_images().toArray()[0].getImage());
			ProductDTO pdto=new ProductDTO();
			BeanUtils.copyProperties(product, pdto);
			return pdto;
		}).collect(Collectors.toList());
		
		return products;
	}

	public ApiResponse getDeleteProduct(Long id) {
		repository.deleteById(id);
		return ApiResponse.builder().message("delete Product successfully!").success(true).build();
	}

}
