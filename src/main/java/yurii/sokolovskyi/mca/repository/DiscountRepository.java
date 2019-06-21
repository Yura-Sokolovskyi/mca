package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
