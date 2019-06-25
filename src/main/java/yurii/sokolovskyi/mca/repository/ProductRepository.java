package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("from Product p where p.category = :category")
    List<Product> findByCategoryId(@Param("category") Category category);



}
