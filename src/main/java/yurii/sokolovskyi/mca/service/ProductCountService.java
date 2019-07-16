package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.ProductCountRequest;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
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

    public void update(ProductCountRequest request) {
        productCountRepository.save(ProductCountRequestToProductCount(productCountRepository
                .findProductCountByCartAndProductId(cartService
                        .getUserCart(request.getUserId()),request.getProductId()), request));
    }

    private ProductCount ProductCountRequestToProductCount(ProductCount productCount, ProductCountRequest request) {
        if (productCount == null) {
            productCount = new ProductCount();
            productCountRepository.save(productCount);
        }
        productCount.setProduct(productService.findOneById(request.getProductId()));
        productCount.setCount(request.getCount());
        productCount.setCart(cartService.getUserCart(request.getUserId()));
        return productCount;
    }



}
