package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.UserRequest;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public Boolean checkUser(UserRequest userRequest) {
        User user = userRepository.getUserByLogin(userRequest.getLogin());
        if(user != null && user.getPassword().equals(userRequest.getPassword())){
            return true;
        } else {

            throw new IllegalArgumentException("Password or user is wrong ");
        }
    }

}
