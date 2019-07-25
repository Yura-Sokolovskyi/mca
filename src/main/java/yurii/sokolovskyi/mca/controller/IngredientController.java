package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yurii.sokolovskyi.mca.dto.request.IngredientCriteria;
import yurii.sokolovskyi.mca.dto.response.IngredientResponse;
import yurii.sokolovskyi.mca.service.IngredientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public List<IngredientResponse> findByCriteria(@Valid @RequestBody IngredientCriteria criteria){
        return ingredientService.findByCriteria(criteria);
    }

}
