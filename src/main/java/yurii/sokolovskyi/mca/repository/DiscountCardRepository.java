package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.DiscountCard;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {
}
