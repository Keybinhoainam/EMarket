package DoAn.B19DCCN445.EMarket.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Product_image;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select distinct p from Product p " + "left join fetch p.category " )
	List<Product> findAllProduct();

//	
	@Query("select distinct p from Product p " + "left join fetch p.category c " 
			+ "where p.id = ?1 ")
	Product findProduct(Long id);

	@Query("select p from Product p "+ "where p.product_name = ?1")
	Product findByProduct_name(String product_name);
//	@Query("select i from Product p " + "left join fetch p.product_images i " + "where p.id = ?1")
//	Product_image findProductImage(Long id);

	@Query("select p from Product p " + "left join fetch p.category " 
			+ "where p.product_name like %?1%")
	List<Product> findByLikeName(String name);
	
	List<Product> findByCategory(Category category);

}
