package DoAn.B19DCCN445.EMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.OrderDTO;
import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.model.Order_detail;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.model.User;
//import DoAn.B19DCCN445.EMarket.model.Payment_type;
import DoAn.B19DCCN445.EMarket.service.OrderService;
import jakarta.websocket.server.PathParam;
//import DoAn.B19DCCN445.EMarket.service.PaymentTypeService;

@RestController
@RequestMapping("/api")
public class OrderController{
//	@Autowired
//	private PaymentTypeService paymentTypeService;
	@Autowired
	private OrderService service;
	
//	@GetMapping("/data/paymenttype/getall")
//	public ResponseEntity<List<Payment_type> >getAllPaymentTypes(){
////		System.out.println("nam");
//		return new ResponseEntity<>(paymentTypeService.getAllPaymentTypes(),HttpStatus.OK);
//	}
	
	@PostMapping("/order/saveOrder")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody Order order){
//		System.out.println(order);
		return ResponseEntity.ok(service.saveOrder(order));
	}
	@PostMapping("/order/changeStatus")
	public ResponseEntity<OrderDTO> changeStatus(@RequestBody OrderDTO order){
		return ResponseEntity.ok(service.changeStatus(order));
	}
	@PostMapping("/order/checkout")
	public ResponseEntity<ApiResponse> checkOut(@RequestBody Order order,@RequestHeader(name = "Authorization", required = true) String authorization){
		return ResponseEntity.ok(service.checkOut(order,authorization));
	}
	@PostMapping("/order/getOrdersByUser")
	public ResponseEntity<List<Order>> getOrdersByUser(@RequestBody User user) throws Exception{
		return ResponseEntity.ok(service.getOrdersByUser(user));
	}
	@PostMapping("/order/getOrdersByStore")
	public ResponseEntity<List<Order>> getOrdersByStore(@RequestBody Store store) throws Exception{
		return ResponseEntity.ok(service.getOrdersByStore(store));
	}
	@GetMapping("/order/getOrder/{id}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") String idOrder){
		return ResponseEntity.ok(service.getOrder(idOrder));
	}
	
}
