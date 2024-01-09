package DoAn.B19DCCN445.EMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DoAn.B19DCCN445.EMarket.model.Order_detail;
import DoAn.B19DCCN445.EMarket.model.Product;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_detail, Long>{
	List<Order_detail> findByProduct(Product product);

}
