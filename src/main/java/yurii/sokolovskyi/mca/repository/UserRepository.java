package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yurii.sokolovskyi.mca.entity.Role;
import yurii.sokolovskyi.mca.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {




}
