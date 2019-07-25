package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Cart;
import yurii.sokolovskyi.mca.entity.Order;
import yurii.sokolovskyi.mca.entity.ProductCount;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductCountRepository extends JpaRepository<ProductCount, Long> {
    ProductCount findProductCountByCartAndProductId(Cart cart , Long productId);

    @Query("SELECT max(pc.updateDateTime) FROM ProductCount pc WHERE pc.order = :_order")
    LocalDateTime findLastOrderUpdateByOrder(@Param("_order") Order order);

    List<ProductCount> findProductCountByOrderId(Long id);



}
