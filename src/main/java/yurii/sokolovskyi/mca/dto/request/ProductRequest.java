package yurii.sokolovskyi.mca.dto.request;


import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.ProductCount;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String image;
    private String info;
    private Double price;
    private Long categoryId;
    private Map<Long,Double> ingredientsCount;

}
