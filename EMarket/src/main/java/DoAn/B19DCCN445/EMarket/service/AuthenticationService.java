package DoAn.B19DCCN445.EMarket.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.common.AuthenticationResponse;
import DoAn.B19DCCN445.EMarket.dto.UserDTO;
import DoAn.B19DCCN445.EMarket.exception.UserAlreadyExistException;
import DoAn.B19DCCN445.EMarket.exception.UserNotFoundException;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.service.AuthenticationService;

@Service
public class AuthenticationService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(UserDTO  acc) throws UserAlreadyExistException {
		
		var user=User.builder().username(acc.getUsername())
				.password(passwordEncoder.encode(acc.getPassword()))
				.fullname(acc.getFullname())
				.gender(acc.getGender())
				.birthday(acc.getBirthday())
				.phone(acc.getPhone())
				.email(acc.getEmail())
				.build();
		if(UserExists(user)) throw new UserAlreadyExistException("There is an account with that username: "
	              											+ user.getUsername());
		accountRepository.save(user);
		var jwtToken=jwtService.genarateToken(user);
		return AuthenticationResponse.builder()
				.accessToken(jwtToken)
				.build();
	}
	private Boolean UserExists(User user) {
		return accountRepository.findByUsername(user.getUsername()).isPresent();
	}
	public AuthenticationResponse authenticate(UserDTO  acc) throws BadCredentialsException,NoSuchElementException, UserNotFoundException{
//		System.out.println(acc.getUsername());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						acc.getUsername(), 
						acc.getPassword()
						)
		);
	
		var user=accountRepository.findByUsername(acc.getUsername())
				.orElseThrow(()->new UserNotFoundException("User not found!"));
		
		var jwtToken=jwtService.genarateToken(user);
//		user=accountRepository.findById(user.getId()).get();
//		System.out.println(user.getId());
//		System.out.println(user.getStore());
		return AuthenticationResponse.builder()
				.accessToken(jwtToken)
				.store(user.getStore())
				.build();
		
	}
	
//	public Boolean checkLogin(AccountDTO accountDTO) {
//		Optional<User> accCheckOpt = accountRepository.findByUsername(accountDTO.getUsername());
//		if(accCheckOpt.isEmpty()) {
//			return false;
//		}
//		else {
//			User accCheck=accCheckOpt.get();
//			return accountDTO.compareUsernamePassword(accCheck);
//			
//		}
//	}
	
	
	
	
}
