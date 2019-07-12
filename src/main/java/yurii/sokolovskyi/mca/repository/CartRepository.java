package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yurii.sokolovskyi.mca.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
