package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Cafe;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
