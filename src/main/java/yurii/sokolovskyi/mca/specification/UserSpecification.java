package yurii.sokolovskyi.mca.specification;

import org.springframework.data.jpa.domain.Specification;
import yurii.sokolovskyi.mca.dto.request.UserCriteria;
import yurii.sokolovskyi.mca.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    private UserCriteria criteria;

    public UserSpecification(UserCriteria userCriteria) {
        this.criteria = userCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.and(findByName(r,cb),findByPhone(r,cb));
    }

    private Predicate findByName (Root<User> r, CriteriaBuilder cb){
        Predicate predicate;
        if (criteria.getName() != null){
            predicate = cb.like(r.get("name"), '%' + criteria.getName() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByPhone (Root<User> r, CriteriaBuilder cb){
        Predicate predicate;
        if (criteria.getPhoneNumber() != null){
            predicate = cb.like(r.get("phoneNumber"), '%' + criteria.getPhoneNumber() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

}
