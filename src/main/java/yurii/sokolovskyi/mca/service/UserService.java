package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.PaginationRequest;
import yurii.sokolovskyi.mca.dto.request.UserCriteria;
import yurii.sokolovskyi.mca.dto.request.UserRequest;
import yurii.sokolovskyi.mca.dto.response.*;
import yurii.sokolovskyi.mca.entity.Product;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.UserRepository;
import yurii.sokolovskyi.mca.specification.UserSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PageResponse<UserSelectResponse> findPage (PaginationRequest paginationRequest){
        Page<User> page = userRepository.findAll(paginationRequest.toPgeable());
        return new PageResponse<>(page.getTotalPages(),
                page.getTotalElements(),
                page.get().map(UserSelectResponse::new).collect(Collectors.toList()));
    }

    public List<UserResponse> findByCriteria (UserCriteria criteria){
        return userRepository.findAll(new UserSpecification(criteria), criteria.getPaginationRequest().toPgeable())
                .stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public List<UserSelectResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserSelectResponse::new)
                .collect(Collectors.toList());
    }

}
