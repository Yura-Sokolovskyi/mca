package yurii.sokolovskyi.mca.specification;

import org.springframework.data.jpa.domain.Specification;
import yurii.sokolovskyi.mca.dto.request.OrderCriteria;
import yurii.sokolovskyi.mca.entity.Order;
import yurii.sokolovskyi.mca.entity.User;

import javax.persistence.criteria.*;


public class OrderSpecification implements Specification<Order> {

    private OrderCriteria criteria;

    public OrderSpecification(OrderCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Order> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        return cb.and(findByName(r, cb), findByDate(r, cb),findByStatus(r, cb));
    }

    private Predicate findByDate(Root<Order> r,CriteriaBuilder cb) {

        Predicate predicate;
        if (criteria.getDate() != null) {
            predicate = cb.equal(r.get("createDateTime"), criteria.getDate());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByStatus(Root<Order> r,CriteriaBuilder cb) {

        Predicate predicate;
        if (criteria.getDate() != null) {
            predicate = cb.equal(r.get("finished"), criteria.getStatus());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByName(Root<Order> r, CriteriaBuilder cb) {
        final Join<Order, User> productCountJoin = r.join("user");
        Predicate predicate;
        if (criteria.getName() != null ) {
            predicate = cb.like(productCountJoin.get("name"), '%' + criteria.getName() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

}