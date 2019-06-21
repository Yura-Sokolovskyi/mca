package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
import yurii.sokolovskyi.mca.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@RequestBody ProductRequest request){
        productService.create(request);
    }

}
