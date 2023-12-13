package DoAn.B19DCCN445.EMarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DoAn.B19DCCN445.EMarket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	@Query("select c from Category c where c.id=?1" )
	@EntityGraph(
		    type = EntityGraphType.FETCH,
		    attributePaths = {
		      "products"
		    }
		  )
	Category findProductsCategoryById(Long id);
	@Query("select c from Category c "+ "left join fetch c.store "+"where c.id=?1")
	Category findCategory(Long id);
	@Query("select c from Category c "+ "left join fetch c.store ")
	List<Category> findAllCategories();
	
	@Query("select c from Category c "+ "left join fetch c.store s " + "where c.store.id=?1")
	List<Category> findAllCategoriesStore(Long id);
	
}
