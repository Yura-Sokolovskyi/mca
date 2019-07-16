package yurii.sokolovskyi.mca.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCountRequest {
    private Long userId;
    private Long productId;
    private Double count;
}
