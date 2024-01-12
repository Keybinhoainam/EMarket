package DoAn.B19DCCN445.EMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DoAn.B19DCCN445.EMarket.model.Order;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.model.User;
@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
	@Query("select o from Order o where o.user=?1 ORDER BY o.id DESC")
	List<Order> findByUser(User user);
	
	@Query("select o from Order o "
			+ " join Order_detail od on o.id=od.order.id "
			+ " join product p on od.product.id=p.id "
			+ " where p.store=?1 "
			+ " ORDER BY o.id DESC")
	List<Order> findByStore(Store store);
//	JOIN OrderDetail ON Order.order_id = OrderDetail.order_id
//			JOIN Product ON OrderDetail.product_id = Product.product_id
//			WHERE Product.store_id = your_store_id;
}
