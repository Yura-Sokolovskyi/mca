package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.ProductCount;

@NoArgsConstructor
@Getter
@Setter
public class CartProductResponse {
    private Long id;
    private Double count;
    private String name;
    private Double price;

    public CartProductResponse(ProductCount productCount) {
        this.id = productCount.getProduct().getId();
        this.count = productCount.getCount();
        this.name = productCount.getProduct().getName();
        this.price =  productCount.getProduct().getPrice()*count;
    }
}
