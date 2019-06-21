package yurii.sokolovskyi.mca.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String image;
    private String info;
    private Double price;
    private Long categoryId;

}
