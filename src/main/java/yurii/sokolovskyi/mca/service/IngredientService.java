package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.IngredientCriteria;
import yurii.sokolovskyi.mca.dto.response.IngredientResponse;
import yurii.sokolovskyi.mca.entity.Ingredient;
import yurii.sokolovskyi.mca.repository.IngredientRepository;
import yurii.sokolovskyi.mca.specification.IngredientSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient findOneById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient with id " + id + " not exists"));
    }


    public List<IngredientResponse> findByCriteria(IngredientCriteria criteria) {
        return ingredientRepository.findAll(new IngredientSpecification(criteria),criteria.getPaginationRequest().toPageable()).stream().map(IngredientResponse::new).collect(Collectors.toList());
    }
}
