package yurii.sokolovskyi.mca.specification;

import org.springframework.data.jpa.domain.Specification;
import yurii.sokolovskyi.mca.dto.request.ProductCriteria;
import yurii.sokolovskyi.mca.dto.request.UserCriteria;
import yurii.sokolovskyi.mca.entity.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product> {

    private ProductCriteria criteria;

    public ProductSpecification(ProductCriteria productCriteria) {
        this.criteria = productCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.and(findByName(r,cb),findByCategory(r,cb));
    }

    private Predicate findByName(Root<Product> r, CriteriaBuilder cb){
        Predicate predicate;
        if (criteria.getName() != null){
            predicate = cb.like(r.get("name"), '%' + criteria.getName() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByCategory(Root<Product> r, CriteriaBuilder cb){
        Predicate predicate;
        if (criteria.getCategoryId() != null){
            predicate = cb.equal(r.get("category").get("id"), criteria.getCategoryId());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

}
