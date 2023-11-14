package DoAn.B19DCCN445.EMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.model.Payment_type;
import DoAn.B19DCCN445.EMarket.service.OrderService;
import DoAn.B19DCCN445.EMarket.service.PaymentTypeService;

@RestController
@RequestMapping("/api")
public class OrderController{
	@Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private OrderService service;
	
	@GetMapping("/data/paymenttype/getall")
	public ResponseEntity<List<Payment_type> >getAllPaymentTypes(){
//		System.out.println("nam");
		return new ResponseEntity<>(paymentTypeService.getAllPaymentTypes(),HttpStatus.OK);
	}
	@PostMapping("/order/checkout")
	public ResponseEntity<ApiResponse> checkOut(@RequestBody Order order,@RequestHeader(name = "Authorization", required = true) String authorization){
		return ResponseEntity.ok(service.checkOut(order,authorization));
	}
	
}
