package DoAn.B19DCCN445.EMarket.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Product_image;
import jakarta.transaction.Transactional;

public interface ProductImageRepository extends JpaRepository<Product_image, Long>{

	@Query("select i from Product_image i where i.product.id=?1")
	List<Product_image> findByProductId(Long id);
	@Transactional 
	@Modifying
	@Query("DELETE FROM Product_image pi WHERE pi.product.id=?1")
	void deleteByProduct(Long id);
//	@Query("select p from Product p " + "left join fetch p.category " + "left join fetch p.store")
//	List<Product> findAllProduct();
//
//	@Query("select p from Product p " + "left join fetch p.category c " + "left join fetch p.store s "
//			+ "left join fetch p.product_images i " + "where p.id = ?1 and i.product.id=?1 ")
//	Product findProduct(Long id);
//
//	@Query("select p from Product p " + "left join fetch p.category " + "left join fetch p.store "
//			+ "where p.product_name like ?1")
//	ArrayList<Product> findByLikeName(String name);

}
