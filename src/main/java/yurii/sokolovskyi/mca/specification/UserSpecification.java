package yurii.sokolovskyi.mca.specification;

import org.springframework.data.jpa.domain.Specification;
import yurii.sokolovskyi.mca.dto.request.UserCriteria;
import yurii.sokolovskyi.mca.entity.Role;
import yurii.sokolovskyi.mca.entity.User;

import javax.persistence.criteria.*;
import java.util.regex.Pattern;

public class UserSpecification implements Specification<User> {

    private UserCriteria criteria;


    public UserSpecification(UserCriteria userCriteria) {
        this.criteria = userCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {



        return cb.and(findByName(r,cb),findByPhone(r,cb),findByRole(r,cb));
    }

    private Predicate findByRole(Root<User> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (criteria.getRole() != null){
            predicate = cb.equal(r.get("role"), criteria.getRole());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }


    private Predicate findByName (Root<User> r, CriteriaBuilder cb){
        Predicate predicate;
        if (criteria.getName() != null && !stringContainsNumber(criteria.getName())){
            predicate = cb.like(r.get("name"), '%' + criteria.getName() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByPhone (Root<User> r, CriteriaBuilder cb){
        Predicate predicate;
        if (criteria.getPhoneNumber() != null && stringContainsNumber(criteria.getPhoneNumber())){
            predicate = cb.like(r.get("phoneNumber"), '%' + criteria.getPhoneNumber() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }


    private boolean stringContainsNumber( String s )
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }

}
