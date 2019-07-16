package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.ProductCountRequest;
import yurii.sokolovskyi.mca.service.ProductCountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/product-count")
public class ProductCountController {

    @Autowired
    private ProductCountService productCountService;

    @PostMapping
    public void create(@Valid @RequestBody ProductCountRequest request) {
        productCountService.create(request);
    }

    @PutMapping
    public void update(@Valid @RequestBody ProductCountRequest request)  {
        productCountService.update(request);
    }

}
