package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurii.sokolovskyi.mca.dto.request.OrderRequest;
import yurii.sokolovskyi.mca.service.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void create(@Valid @RequestBody OrderRequest request){ orderService.create(request);}

}
