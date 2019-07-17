package yurii.sokolovskyi.mca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yurii.sokolovskyi.mca.entity.Cart;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.entity.Product;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {



}
