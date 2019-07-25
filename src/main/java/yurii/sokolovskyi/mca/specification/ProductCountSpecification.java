package yurii.sokolovskyi.mca.specification;

import org.springframework.data.jpa.domain.Specification;
import yurii.sokolovskyi.mca.entity.ProductCount;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductCountSpecification implements Specification<ProductCount> {
    @Override
    public Predicate toPredicate(Root<ProductCount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
