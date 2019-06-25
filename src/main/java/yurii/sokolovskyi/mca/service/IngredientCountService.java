package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.IngredientCountRequest;
import yurii.sokolovskyi.mca.entity.*;
import yurii.sokolovskyi.mca.repository.IngredientCountRepository;

import java.util.List;

@Service
public class IngredientCountService {

    @Autowired
    private IngredientCountRepository ingredientCountRepository;

    @Autowired
    private IngredientService ingredientService;

    public void create(Double count, Long ingredientId, Product product, Invoice invoice){
        IngredientCount ingredientCount = new IngredientCount();
        ingredientCount.setCount(count);
        ingredientCount.setIngredient(ingredientService.findOneById(ingredientId));
        ingredientCount.setProduct(product);
        ingredientCount.setInvoice(invoice);
        ingredientCountRepository.save(ingredientCount);
    }



}
