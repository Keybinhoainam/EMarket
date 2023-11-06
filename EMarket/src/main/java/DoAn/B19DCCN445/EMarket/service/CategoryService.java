package DoAn.B19DCCN445.EMarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.repository.CategoryRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	@Autowired
	private ProductRepository productRepository;

	public Category getCategory(Long id) {
		// TODO Auto-generated method stub
//		System.out.println("nam");
//		System.out.println(repository.getReferenceById(id));
		return repository.findById(id).get();
	}

	public ApiResponse saveCategory(Category category) {
		// TODO Auto-generated method stub
		repository.save(category);
		return ApiResponse.builder().message("save category successfully!").success(true).build();
	}

	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return repository.findAll();
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

}
