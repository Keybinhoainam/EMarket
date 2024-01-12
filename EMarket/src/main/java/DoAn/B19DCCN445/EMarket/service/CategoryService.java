package DoAn.B19DCCN445.EMarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.CategoryDTO;
import DoAn.B19DCCN445.EMarket.dto.ProductDTO;
import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.repository.CategoryRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	@Autowired
	private ProductRepository productRepository;

	public CategoryDTO getCategory(Long id) {
		// TODO Auto-generated method stub
//		System.out.println("nam");
//		System.out.println(repository.getReferenceById(id));
		Category category=repository.findCategory(id);
		CategoryDTO categoryDTO=new CategoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
	}

	public ApiResponse saveCategory(CategoryDTO categoryDto) {
//		// TODO Auto-generated method stub
		Category category=new Category();
		BeanUtils.copyProperties(categoryDto, category);
		repository.save(category);
		return ApiResponse.builder().message("save category successfully!").success(true).build();
	}

	public List<CategoryDTO> getAllCategories() {
		List<CategoryDTO> dtos=repository.findAllCategories().stream().map((category)->{
//			List<Product_image> list=new ArrayList<>(product.getProduct_images());
//			System.out.println(list.get(0).getImage());
			CategoryDTO cdto=new CategoryDTO();
			BeanUtils.copyProperties(category, cdto);
//			List<Product_image> list2=new ArrayList<>(pdto.getProduct_images());
//			System.out.println(list2.get(0).getImage());
			return cdto;
		}).collect(Collectors.toList());
//		System.out.println(dtos.size());
		// TODO Auto-generated method stub
		return dtos;
	}

	public ApiResponse getDeleteCategory(Long id) {
		repository.deleteById(id);
		return ApiResponse.builder().message("delete category successfully!").success(true).build();
	}

	public Category getProductsCategory(Long categoryId) {
		Category category=repository.findProductsCategoryById(categoryId);
//		List<Product> products=productRepository.findByCategory(category);
//		category.setProducts(products);
		return category;
	}

//	public List<CategoryDTO> getAllCategoriesStore(Store store) {
//		List<CategoryDTO> dtos=repository.findAllCategoriesStore(store.getId()).stream().map((category)->{
////			List<Product_image> list=new ArrayList<>(product.getProduct_images());
////			System.out.println(list.get(0).getImage());
//			CategoryDTO cdto=new CategoryDTO();
//			BeanUtils.copyProperties(category, cdto);
////			List<Product_image> list2=new ArrayList<>(pdto.getProduct_images());
////			System.out.println(list2.get(0).getImage());
//			return cdto;
//		}).collect(Collectors.toList());
//		// TODO Auto-generated method stub
//		return dtos;
//	}

}
