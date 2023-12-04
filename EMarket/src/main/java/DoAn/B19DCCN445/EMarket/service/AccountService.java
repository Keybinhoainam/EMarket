package DoAn.B19DCCN445.EMarket.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.UserDTO;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.service.AccountService;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public List<User> getAccounts() {
		List<User> list=accountRepository.findAll();
		return list;
	}
	public ApiResponse saveAccount(UserDTO uDto) {
		User u=new User();
		BeanUtils.copyProperties(uDto, u);
		accountRepository.save(u);
		return ApiResponse.builder().success(true).message("Save Account Successfully").build();
	}
	
	
	
}
