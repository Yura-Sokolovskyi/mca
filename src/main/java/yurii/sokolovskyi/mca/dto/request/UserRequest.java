package yurii.sokolovskyi.mca.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String name;
    private String phoneNumber;
    private String password;
    private String login;
    private List<Long> invoicesId;
    private List<Long> rolesId;
    private List<Long> cafesId;
    private List<Long> ordersId;
    private List<Long> discountsId;
    private Long discountCardId;
    private Long addressId;

}
