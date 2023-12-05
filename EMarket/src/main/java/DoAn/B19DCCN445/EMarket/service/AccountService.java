package DoAn.B19DCCN445.EMarket.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DoAn.B19DCCN445.EMarket.common.ApiResponse;
import DoAn.B19DCCN445.EMarket.dto.UserDTO;
import DoAn.B19DCCN445.EMarket.exception.StorageException;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.service.AccountService;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	FilesStorageService storageService;
	public List<User> getAccounts() {
		List<User> list=accountRepository.findAll();
		return list;
	}
	public ApiResponse saveAccount(UserDTO uDto) {
		User u=accountRepository.findById(uDto.getId()).get();
//		u.setAvatar(storeImageAccount(avatar));
		accountRepository.save(u);
		return ApiResponse.builder().success(true).message("Save Account Successfully").build();
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
	
	
	
}
