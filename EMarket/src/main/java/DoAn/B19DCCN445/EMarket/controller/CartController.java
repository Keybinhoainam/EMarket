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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.CartDTO;
import DoAn.B19DCCN445.EMarket.dto.ProductDTO;
import DoAn.B19DCCN445.EMarket.exception.ProductNotFoundException;
import DoAn.B19DCCN445.EMarket.model.Cart_detail;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.service.CartDetailService;
import DoAn.B19DCCN445.EMarket.service.ProductService;

@RestController
@RequestMapping("/api/customer/cart")
public class CartController {
	@Autowired
	private CartDetailService service;
	
	@PostMapping("/save")
//	@RequestParam("files") String filesJson,@RequestParam("product") String productJson
	private ResponseEntity<ApiResponse> saveCart( @RequestBody CartDTO cartDTO){
		return ResponseEntity.ok(service.saveCart(cartDTO));
	}

	@GetMapping("")
	private ResponseEntity<CartDTO> getAllCart_details(@RequestHeader(name = "Authorization", required = true) String authorization) {
		return ResponseEntity.ok(service.getAllCart_details(authorization));
	}

}
