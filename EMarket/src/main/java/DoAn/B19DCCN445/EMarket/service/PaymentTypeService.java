package DoAn.B19DCCN445.EMarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DoAn.B19DCCN445.EMarket.model.Payment_type;
import DoAn.B19DCCN445.EMarket.repository.PaymentTypeRepository;

@Service
public class PaymentTypeService {
	@Autowired
	PaymentTypeRepository repository;
	
	public List<Payment_type> getAllPaymentTypes(){
		return repository.findAll();
	}
}
