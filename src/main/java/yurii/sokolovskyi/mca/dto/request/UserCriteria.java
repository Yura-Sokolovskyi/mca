package yurii.sokolovskyi.mca.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCriteria {
    private String name;
    private String phoneNumber;

    @NotNull
    private PaginationRequest paginationRequest;
}
