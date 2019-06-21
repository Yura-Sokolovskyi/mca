package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.entity.Measure;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long> {
}
