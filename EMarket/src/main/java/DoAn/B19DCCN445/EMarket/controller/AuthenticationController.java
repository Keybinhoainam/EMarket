package DoAn.B19DCCN445.EMarket.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DoAn.B19DCCN445.EMarket.common.AuthenticationResponse;
import DoAn.B19DCCN445.EMarket.dto.UserDTO;
import DoAn.B19DCCN445.EMarket.exception.UserAlreadyExistException;
import DoAn.B19DCCN445.EMarket.exception.UserNotFoundException;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService service;
	
	
//	@PostMapping("/login")
//	public ResponseEntity<?> checkLogin(@RequestBody AccountDTO  acc) {
//		return new ResponseEntity<>(service.checkLogin(acc),HttpStatus.ACCEPTED);
//	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> checkRegister(
			@RequestBody UserDTO  acc
	) throws UserAlreadyExistException {
		return ResponseEntity.ok(service.register(acc));
	}
	@PostMapping("/login")
	public ResponseEntity<UserDTO> authenticate(
			@RequestBody User  acc
	) throws BadCredentialsException, NoSuchElementException, UserNotFoundException {
		System.out.println(acc.getPassword());
		return ResponseEntity.ok(service.authenticate(acc));
	}
}
