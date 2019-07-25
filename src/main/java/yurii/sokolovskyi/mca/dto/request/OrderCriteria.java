package yurii.sokolovskyi.mca.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCriteria {
    private String name;
    private LocalDateTime date;
    private Boolean status;

    @NotNull
    private PaginationRequest paginationRequest;
}
