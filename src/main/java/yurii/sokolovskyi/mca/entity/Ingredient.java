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
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double quantity;

    private Double price;

    @ManyToOne
    private Measure measure;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientCount> ingredientsCounts = new ArrayList<>();
}
