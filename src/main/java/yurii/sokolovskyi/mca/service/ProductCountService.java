package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.ProductCountRequest;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
import yurii.sokolovskyi.mca.dto.response.CartProductResponse;
import yurii.sokolovskyi.mca.entity.Product;
import yurii.sokolovskyi.mca.entity.ProductCount;
import yurii.sokolovskyi.mca.repository.CartRepository;
import yurii.sokolovskyi.mca.repository.ProductCountRepository;

import java.util.Map;


@Service
public class ProductCountService {

    @Autowired
    private ProductCountRepository productCountRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;



    public void create(ProductCountRequest request) {
        productCountRepository.save(ProductCountRequestToProductCount(null, request));
    }

    public CartProductResponse update(ProductCountRequest request) {

        CartProductResponse cartProductResponse;

        ProductCount productCount = getProductCount(request.getUserId(),request.getProductId());

        if (request.getSign().equals("-") && productCount.getCount() == 1){
            delete(request.getUserId(),request.getProductId());
            cartProductResponse = null;
        } else {
            ProductCount productCountUpdated = ProductCountRequestToProductCount(productCount, request);
            productCountRepository.save(productCountUpdated);
            cartProductResponse = new CartProductResponse(productCountUpdated);
        }
        return cartProductResponse;
    }

    private ProductCount ProductCountRequestToProductCount(ProductCount productCount, ProductCountRequest request) {
        if (productCount == null) {
            productCount = new ProductCount();
            productCountRepository.save(productCount);
            productCount.setProduct(productService.findOneById(request.getProductId()));
            productCount.setCart(cartService.getUserCart(request.getUserId()));
            productCount.setCount(1.0);
        } else {
        if(request.getSign().equals("+") ){
            productCount.setCount(productCount.getCount()+1);
        } else if (request.getSign().equals("-")){
            productCount.setCount(productCount.getCount()-1);
        } else {
            productCount.setCount(request.getCount());
        }}
        return productCount;
    }

    private ProductCount getProductCount(Long userId, Long productId){
        return productCountRepository
                .findProductCountByCartAndProductId(cartService
                        .getUserCart(userId),productId);
    }

    public void delete(Long userId, Long productId){
        productCountRepository.delete(getProductCount(userId,productId));
    }

}
