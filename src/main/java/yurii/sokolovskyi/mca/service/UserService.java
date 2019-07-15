package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.UserRequest;
import yurii.sokolovskyi.mca.dto.response.CategoryResponse;
import yurii.sokolovskyi.mca.dto.response.UserSelectResponse;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserSelectResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserSelectResponse::new)
                .collect(Collectors.toList());
    }

}
