package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
import yurii.sokolovskyi.mca.dto.response.ProductResponse;
import yurii.sokolovskyi.mca.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@RequestBody ProductRequest request){
        productService.create(request);
    }

    @GetMapping
    public List<ProductResponse> findByCategory(Long id) {return productService.findByCategory(id);}

    @DeleteMapping ("/deleteByCategory")
    public void deleteProductsByCategory(Long id) {productService.deleteProductsByCategory(id);}

}
