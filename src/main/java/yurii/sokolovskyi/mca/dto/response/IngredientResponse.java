package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.Ingredient;

@Getter
@Setter
public class IngredientResponse {

    private Long id;
    private String name;
    private Double price;
    private Double quantity;
    private String measure;

    public IngredientResponse(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
        this.quantity = ingredient.getQuantity();
        this.measure = ingredient.getMeasure().getType();
    }
}
