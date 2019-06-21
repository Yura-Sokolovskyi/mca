package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.ProductCount;

@Repository
public interface ProductCountRepository extends JpaRepository<ProductCount, Long> {
}
