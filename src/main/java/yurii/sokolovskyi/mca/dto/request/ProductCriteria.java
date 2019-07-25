package yurii.sokolovskyi.mca.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProductCriteria {

    private String name;
    private Long categoryId;

    @NotNull
    private PaginationRequest paginationRequest;

}
