package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.Product;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String image;
    private Double price;
    private String info;
    private String category;


    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        image = product.getImage();
        price = product.getPrice();
        info = product.getInfo();
        category = product.getCategory().getName();
    }
}
