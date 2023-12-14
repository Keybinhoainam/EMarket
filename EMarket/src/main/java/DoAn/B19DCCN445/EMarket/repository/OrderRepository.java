package DoAn.B19DCCN445.EMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.model.User;
@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
	@Query("select o from Order o where o.user=?1 ORDER BY o.id DESC")
	List<Order> findByUser(User user);
	
}
