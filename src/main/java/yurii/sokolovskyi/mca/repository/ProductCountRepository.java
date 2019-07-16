package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Cart;
import yurii.sokolovskyi.mca.entity.ProductCount;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductCountRepository extends JpaRepository<ProductCount, Long> {

    ProductCount findProductCountByCartAndProductId(Cart cart , Long productId);


}
