package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurii.sokolovskyi.mca.dto.response.DiscountResponse;
import yurii.sokolovskyi.mca.service.DiscountService;

@RestController
@RequestMapping ("/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public DiscountResponse getDiscount(Long id){return discountService.getUserDiscountResponse(id);}

}
