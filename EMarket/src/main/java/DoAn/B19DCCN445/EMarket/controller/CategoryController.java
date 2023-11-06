package DoAn.B19DCCN445.EMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/data/category/get/{id}")
	private ResponseEntity<Category> getCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategory(id));
	}
	@PostMapping("/seller/category/save")
	private ResponseEntity<ApiResponse> saveCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.saveCategory(category));
	}
	@GetMapping("/data/category/getallcategories")
	private ResponseEntity<List<Category>> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	@DeleteMapping("/seller/category/delete/{id}")
	private ResponseEntity<ApiResponse> getDeleteCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getDeleteCategory(id));
	}
	@GetMapping("/data/category/product/get/{categoryId}")
	private ResponseEntity<Category> getProductsCategory(@PathVariable("categoryId") Long categoryId) {
		Category category=categoryService.getProductsCategory(categoryId);
		return ResponseEntity.ok(category);
	}
}
