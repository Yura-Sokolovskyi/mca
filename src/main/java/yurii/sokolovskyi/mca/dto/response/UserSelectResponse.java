package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.User;

@Getter @Setter
public class UserSelectResponse {

    private Long id;
    private String name;

    public UserSelectResponse(User user) {
        id = user.getId();
        name = user.getName();
    }
}
