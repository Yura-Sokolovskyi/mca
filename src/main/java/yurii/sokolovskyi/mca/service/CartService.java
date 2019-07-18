package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.ProductCountRequest;
import yurii.sokolovskyi.mca.dto.response.CartProductResponse;
import yurii.sokolovskyi.mca.entity.Cart;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.CartRepository;
import yurii.sokolovskyi.mca.repository.ProductCountRepository;
import yurii.sokolovskyi.mca.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductCountService productCountService;

    @Autowired
    private UserRepository userRepository;


    public List<CartProductResponse> getProductsByUser(Long id){
        return getUserCart(id).getProductCounts().stream()
                .map(CartProductResponse::new).collect(Collectors.toList());
    }


    public Cart getUserCart(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not exists")).getCart();
    }

}
