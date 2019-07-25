package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.OrderCriteria;
import yurii.sokolovskyi.mca.dto.request.OrderRequest;
import yurii.sokolovskyi.mca.dto.response.OrderResponse;
import yurii.sokolovskyi.mca.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/find-by-filter")
    public List<OrderResponse> getAllByCriteria(@Valid @RequestBody OrderCriteria criteria){return orderService.findByCriteria(criteria);}

    @PostMapping
    public void create(@Valid @RequestBody OrderRequest request){ orderService.create(request);}

    @DeleteMapping
    public void delete(Long id){orderService.delete(id);}

}
