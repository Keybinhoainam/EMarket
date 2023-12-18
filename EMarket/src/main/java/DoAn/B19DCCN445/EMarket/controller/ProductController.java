package DoAn.B19DCCN445.EMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.ProductDTO;
import DoAn.B19DCCN445.EMarket.exception.ProductNotFoundException;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/data/product/get/{id}")
	private ResponseEntity<ProductDTO> Product(@PathVariable Long id) throws ProductNotFoundException {
		return ResponseEntity.ok(productService.getProduct(id));
	}
	@PostMapping("/seller/product/save")
//	@RequestParam("files") String filesJson,@RequestParam("product") String productJson
	private ResponseEntity<ApiResponse> saveProduct( @RequestBody Product product){
//		System.out.println(product.getId());
		return ResponseEntity.ok(productService.saveProduct(product));
	}
	
	@PostMapping("/seller/product/images/save")
	private ResponseEntity<ApiResponse> saveProductImages( @RequestParam("files") MultipartFile[] files, @RequestParam("product_name") String product_name){
		return ResponseEntity.ok(productService.saveProductImages(files,product_name));
	}
	
	@GetMapping("/data/product/getallproducts")
	private ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}
	@PostMapping("/data/product/getAllProductsStore")
	private ResponseEntity<List<ProductDTO>> getAllProductsStore(@RequestBody Store store) {
		return ResponseEntity.ok(productService.getAllProductsStore(store));
	}
	@GetMapping("/data/product/getproductslikename")
	private ResponseEntity<List<ProductDTO>> getProductsLikeName(@RequestParam("name") String name) {
		return ResponseEntity.ok(productService.getProductsLikeName(name));
	}
	
	@DeleteMapping("/seller/product/delete/{id}")
	private ResponseEntity<ApiResponse> getDeleteProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getDeleteProduct(id));
	}
}
