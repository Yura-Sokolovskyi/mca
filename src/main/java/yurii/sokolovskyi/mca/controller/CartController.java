package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurii.sokolovskyi.mca.dto.response.CartProductResponse;
import yurii.sokolovskyi.mca.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/get-user-cart")
    public List<CartProductResponse> getAllForSelect(Long id){return cartService.getProductsByUser(id);}


}
