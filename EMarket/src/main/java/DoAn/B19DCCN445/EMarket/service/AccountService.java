package DoAn.B19DCCN445.EMarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.UserDTO;
import DoAn.B19DCCN445.EMarket.exception.StorageException;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Role;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.repository.RoleRepository;
import DoAn.B19DCCN445.EMarket.service.AccountService;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	FilesStorageService storageService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;
	public List<User> getAccounts() {
		List<User> list=accountRepository.findAll();
		return list;
	}
	public UserDTO saveAccount(UserDTO uDto) {
		User u=accountRepository.findById(uDto.getId()).get();
		uDto.setPassword(u.getPassword());
		uDto.setAvatar(u.getAvatar());
		List<Role> roles=new ArrayList<>(u.getRoles());
		if(u.getStore().getId()!=null&&roles.get(0).getName().equals("CUSTOMER")) {
			Role role= roleRepository.findById((long) 2).get();
			roles.add(0,role);
		}
		uDto.setRoles(roles);
		BeanUtils.copyProperties(uDto, u);
		
		accountRepository.save(u);
		uDto.setAvatarFile(null);
		return uDto;
	}
	public String storeImageAccount(MultipartFile file) {
		// TODO Auto-generated method stub
		UUID uuid=UUID.randomUUID();
		String uuString=uuid.toString();
		
		return storageService.getStoredFilename(file, uuString);
	}
	public ApiResponse saveAvatarAccount(MultipartFile avatar, Long id) {
		// TODO Auto-generated method stub
		User user=accountRepository.findById(id).get();
		String avatarString=storeImageAccount(avatar);
		user.setAvatar(avatarString);
		try {
			storageService.store(avatar, avatarString);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accountRepository.save(user);
		return ApiResponse.builder().success(true).message("Save Image Account Successfully").build();
	}
	public ApiResponse checkCurrentPassword(UserDTO u) throws Exception {
		User tmp=accountRepository.findByUsername(u.getUsername()).get();
		if(!encoder.matches(u.getCurrentPassword(), tmp.getPassword())) {
			return ApiResponse.builder().success(false).message("Change password Fail").build();
		}
		tmp.setPassword(encoder.encode(u.getPassword()));
		accountRepository.save(tmp);
		// TODO Auto-generated method stub
		return ApiResponse.builder().success(true).message("Change password Successfully").build();
	}
	
	
	
}
