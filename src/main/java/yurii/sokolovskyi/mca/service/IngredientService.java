package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.entity.Ingredient;
import yurii.sokolovskyi.mca.repository.IngredientRepository;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient findOneById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient with id " + id + " not exists"));
    }

}
