package yurii.sokolovskyi.mca.dto.request;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.Role;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCriteria {

    private String name;
    private String phoneNumber;
    private Role role;

    @NotNull
    private PaginationRequest paginationRequest;
}
