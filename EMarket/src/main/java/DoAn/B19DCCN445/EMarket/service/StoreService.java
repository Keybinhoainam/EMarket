package DoAn.B19DCCN445.EMarket.service;

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
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.repository.AccountRepository;
import DoAn.B19DCCN445.EMarket.repository.StoreRepository;
import DoAn.B19DCCN445.EMarket.service.StoreService;

@Service
public class StoreService {
	@Autowired
	private StoreRepository repository;
	@Autowired
	FilesStorageService storageService;
	@Autowired
	PasswordEncoder encoder;
	public Store saveStore(Store store) {
		return repository.save(store);
	}
	public String storeImageStore(MultipartFile file) {
		// TODO Auto-generated method stub
		UUID uuid=UUID.randomUUID();
		String uuString=uuid.toString();
		
		return storageService.getStoredFilename(file, uuString);
	}
	public Store saveImageStore(MultipartFile avatar, Long id) {
		// TODO Auto-generated method stub
		Store store=repository.findById(id).get();
		String imageString=storeImageStore(avatar);
		store.setImage(imageString);
		try {
			storageService.store(avatar, imageString);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(store.getImage());
		return repository.save(store);
	}
	
	
	
	
}
