package DoAn.B19DCCN445.EMarket.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Product_image;
import DoAn.B19DCCN445.EMarket.model.Product_review;

public interface ProductReviewRepository extends JpaRepository<Product_review, Long> {
	@Query("select pr from Product_review pr " + "left join fetch pr.user u " +"where pr.product=?1")
	List<Product_review> findByProduct(Product p);
	

}
