package DoAn.B19DCCN445.EMarket.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.model.Order_detail;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AccountRepository accountRepository;
	
	public ApiResponse checkOut(Order order,String authHeader) {
		String jwt;
		String username=null;
		jwt=authHeader.substring(7);
		try {
			username=jwtService.extractUsername(jwt);
		} catch (Exception e) {
			log.error("Error logging in: {}",e.getMessage());
			return null;
		}
		User user= accountRepository.findByUsername(username).get();
		order.setUser(user);
		Collection<Order_detail> order_details=order.getOrder_details();
		order.setOrder_details(null);
		order=repository.save(order);
		System.out.println(order.getId());
		order.setOrder_details(order_details);
		order=repository.save(order);
		return ApiResponse.builder().message("CheckOut successfully!").success(true).build();
	}
}
