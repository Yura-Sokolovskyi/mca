package yurii.sokolovskyi.mca.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class IngredientCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double count;

    @ManyToOne
    private Invoice invoice;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private Product product;


}
