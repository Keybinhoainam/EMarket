package DoAn.B19DCCN445.EMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DoAn.B19DCCN445.EMarket.model.Payment_type;
@Repository
public interface PaymentTypeRepository extends JpaRepository<Payment_type, Long>{

}
