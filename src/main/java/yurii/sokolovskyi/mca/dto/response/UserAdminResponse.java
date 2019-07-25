package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.Address;
import yurii.sokolovskyi.mca.entity.Discount;
import yurii.sokolovskyi.mca.entity.DiscountCard;
import yurii.sokolovskyi.mca.entity.User;

import java.time.LocalDate;

@Getter
@Setter
public class UserAdminResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private String password;
    private String login;
    private Boolean active;
    private LocalDate birthDate;
    private String role;

    public UserAdminResponse(User user ) {
        id = user.getId();
        name = user.getName();
        phoneNumber = user.getPhoneNumber();
        password = user.getPassword();
        login = user.getLogin();
        active = user.getActive();
        birthDate = user.getBirthDate();
        role = user.getRole().toString();
    }
}
