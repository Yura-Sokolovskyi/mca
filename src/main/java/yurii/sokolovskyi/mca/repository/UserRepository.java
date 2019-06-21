package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
