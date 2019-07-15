package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.User;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private String phone;

    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        phone = user.getPhoneNumber();
    }
}
