package yurii.sokolovskyi.mca.specification;


import org.springframework.data.jpa.domain.Specification;
import yurii.sokolovskyi.mca.dto.request.IngredientCriteria;
import yurii.sokolovskyi.mca.entity.Ingredient;

import javax.persistence.criteria.*;

public class IngredientSpecification implements Specification<Ingredient> {

    private IngredientCriteria criteria;

    public IngredientSpecification(IngredientCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Ingredient> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.and(findByName(r,cb));
    }

    private Predicate findByName(Root<Ingredient> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (criteria.getName() != null ) {
            predicate = cb.like(r.get("name"), '%' + criteria.getName() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

}
