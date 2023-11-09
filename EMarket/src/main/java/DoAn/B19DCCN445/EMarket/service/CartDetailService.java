package DoAn.B19DCCN445.EMarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.CartDTO;
import DoAn.B19DCCN445.EMarket.model.Cart_detail;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.repository.CartDetailRepository;
import DoAn.B19DCCN445.EMarket.service.CartDetailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartDetailService {
	@Autowired
	private CartDetailRepository repository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AccountRepository accountRepository;

	public CartDTO getAllCart_details(String authHeader) {
		final String jwt;
		String username=null;
		jwt=authHeader.substring(7);
		try {
			username=jwtService.extractUsername(jwt);
		} catch (Exception e) {
			log.error("Error logging in: {}",e.getMessage());
			return null;
		}
		User user= accountRepository.findByUsername(username).get();
		CartDTO cartDTO=new CartDTO();
		List<Cart_detail> cart_details=repository.findByUser(user);
//		System.out.println(cart_details.get(0).getId());
		cartDTO.setCart_details(cart_details);
		return cartDTO;
	}

	public ApiResponse saveCart(CartDTO cartDTO) {
		// TODO Auto-generated method stub
		cartDTO.getCart_details().stream().forEach(cartDetail->{
			repository.save(cartDetail);
		});
		return ApiResponse.builder().message("save Cart successfully!").success(true).build();
	}
	
//	public List<User> getAccounts() {
//		List<User> list=accountRepository.findAll();
//		return list;
//	}
	
	
	
}
