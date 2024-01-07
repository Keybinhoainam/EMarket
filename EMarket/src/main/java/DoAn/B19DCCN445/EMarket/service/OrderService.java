package DoAn.B19DCCN445.EMarket.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.OrderDTO;
import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.model.Order_detail;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.repository.OrderDetailRepository;
import DoAn.B19DCCN445.EMarket.repository.OrderRepository;
import DoAn.B19DCCN445.EMarket.repository.ProductRepository;
import DoAn.B19DCCN445.EMarket.zalopay.GetZaloOrderStatus;
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
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private ProductRepository productRepository;
	
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
		for(Order_detail order_detail: order_details) {
			Product product=productRepository.findById(order_detail.getProduct().getId()).get();
			product.setStock(product.getStock()-order_detail.getQuantity());
			productRepository.save(product);
			order_detail.setOrder(order);
			orderDetailRepository.save(order_detail);
		}
		return ApiResponse.builder().message("CheckOut successfully!").success(true).build();
	}

	public OrderDTO saveOrder(Order order) {
		Collection<Order_detail> order_details=order.getOrder_details();
		order.setOrder_details(null);
		order.setOrder_date(new Date());;
		order=repository.save(order);
		for(Order_detail order_detail: order_details) {
			Product product=productRepository.findById(order_detail.getProduct().getId()).get();
			product.setStock(product.getStock()-order_detail.getQuantity());
			productRepository.save(product);
			order_detail.setOrder(order);
			orderDetailRepository.save(order_detail);
		}
		order=repository.save(order);
//		System.out.println(order.getOrder_details()!=null?order.getOrder_details().size():"null");
		OrderDTO orderDTO=new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO);
		return orderDTO;
	}

	public OrderDTO changeStatus(OrderDTO request) {
		OrderDTO orderDTO= new OrderDTO();
		Collection<Order_detail> order_details=request.getOrder_details();
		for(Order_detail order_detail: order_details) {
			Product product=productRepository.findById(order_detail.getProduct().getId()).get();
			product.setStock(product.getStock()+order_detail.getQuantity());
			productRepository.save(product);
		}
		Order order= repository.findById(request.getId()).get();
		order.setOrder_status(request.getOrder_status());
		repository.save(order);
		BeanUtils.copyProperties(order, orderDTO);
		
		return orderDTO;
	}
	public void changeQuantityProductOrder(Order order) {
		Collection<Order_detail> order_details=order.getOrder_details();
		for(Order_detail order_detail: order_details) {
			Product product=productRepository.findById(order_detail.getProduct().getId()).get();
			product.setStock(product.getStock()+order_detail.getQuantity());
			productRepository.save(product);
		}
	}
	public List<Order> getOrdersByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		List<Order> orders=repository.findByUser(user);
		LocalDateTime currentDateTime = LocalDateTime.now();
		for(Order order :orders  ) {
//			System.out.println(order.getOrder_details().size());
			LocalDateTime time = order.getOrder_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if( order.getOrder_status().equals("Unpaid")) {
				int zaloOrderStatus=GetZaloOrderStatus.main(order);
				if(ChronoUnit.MINUTES.between(time, currentDateTime) >= 15) {
					order.setOrder_status("Cancelled");
					changeQuantityProductOrder(order);
					
				}
				else if(zaloOrderStatus==1){
					order.setOrder_status("Order Placed");
					order.setPaid_date(new Date());
				}
				else if(zaloOrderStatus==2){
					order.setOrder_status("Cancelled");
					changeQuantityProductOrder(order);
					
				}
				repository.save(order);
			}
		}
		return orders;
	}

	public OrderDTO getOrder(String idOrder) {
		Order order=repository.findById(idOrder).get();
		OrderDTO dto=new OrderDTO();
		BeanUtils.copyProperties(order, dto);
		Double amount=(double) 0;
		for(Order_detail order_detail : dto.getOrder_details()) {
			amount+=order_detail.getUnit_price();
		}
		dto.setAmount(amount);
		// TODO Auto-generated method stub
		return dto;
	}
}
