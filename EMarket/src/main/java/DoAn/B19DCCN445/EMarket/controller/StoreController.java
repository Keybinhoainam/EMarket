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
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.service.AccountService;
import DoAn.B19DCCN445.EMarket.service.StoreService;

@RestController
@RequestMapping("/api")
public class StoreController {
	
	@Autowired
	private StoreService service;
	
	@RequestMapping("/store/saveStore")
	public ResponseEntity<Store> saveStore(@RequestBody Store store){
		return new ResponseEntity<>(service.saveStore(store),HttpStatus.OK);
	}
	@PostMapping("/store/saveImageStore")
	public ResponseEntity<Store> saveImageStore(@RequestParam("image") MultipartFile avatar,@RequestParam("idStore") Long id){

		return new ResponseEntity<>(service.saveImageStore(avatar,id),HttpStatus.OK);
	}
}
