package yurii.sokolovskyi.mca.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IngredientCountRequest {

    private Double count;
    private Long invoiceId;
    private Long ingredientId;
    private Long productId;

}
