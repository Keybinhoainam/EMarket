package DoAn.B19DCCN445.EMarket.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		System.out.println("ok");
		return new ResponseEntity<>(service.saveAccount(u),HttpStatus.OK);
	}
	@RequestMapping("/account/saveAvatarAccount")
	public ResponseEntity<ApiResponse> saveAvatarAccount(@RequestParam("avatar") MultipartFile avatar,@RequestParam("idAccount") Long id){
//		System.out.println("ok");
		return new ResponseEntity<>(service.saveAvatarAccount(avatar,id),HttpStatus.OK);
	}
//	@PostMapping("/seller/account/image/save")
//	private ResponseEntity<ApiResponse> saveProductImages( @RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
//		return ResponseEntity.ok(service.saveImageAccount(file,id));
//	}
}
