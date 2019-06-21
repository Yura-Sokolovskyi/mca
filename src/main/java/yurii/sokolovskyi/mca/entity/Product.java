package yurii.sokolovskyi.mca.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private String info;
    
    private Double price;

    @ManyToOne
    private Category category;

    @OneToMany (mappedBy = "product")
    private List<ProductCount> productCounts = new ArrayList<>();

    @OneToMany (mappedBy = "product")
    private List<IngredientCount> ingredientsCounts = new ArrayList<>();

}
