package DoAn.B19DCCN445.EMarket.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.UserDTO;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@RequestMapping("/data/account/getallcccount")
	public ResponseEntity<List<User>> getAccounts(){
		return new ResponseEntity<>(service.getAccounts(),HttpStatus.OK);
	}
	@RequestMapping("/account/saveAccount")
	public ResponseEntity<ApiResponse> saveAccount(@RequestBody UserDTO u){
		return new ResponseEntity<>(service.saveAccount(u),HttpStatus.OK);
	}
}
