package DoAn.B19DCCN445.EMarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DoAn.B19DCCN445.EMarket.model.Cart_detail;
import DoAn.B19DCCN445.EMarket.model.User;
@Repository
public interface CartDetailRepository extends JpaRepository<Cart_detail, Long>{
//	@Query("select u from User u " + "left join fetch u.store s "
//			 + "where u.username = ?1 ")
//	Optional<User> findByUsername(String username);
	List<Cart_detail> findByUser(User user);
}
