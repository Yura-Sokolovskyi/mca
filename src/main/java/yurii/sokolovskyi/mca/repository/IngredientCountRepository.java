package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.IngredientCount;

@Repository
public interface IngredientCountRepository extends JpaRepository<IngredientCount, Long> {
}
