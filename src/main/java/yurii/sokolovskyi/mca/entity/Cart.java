package yurii.sokolovskyi.mca.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalTime time;

    @OneToMany (mappedBy = "cart")
    private List<User> users;

    @OneToMany(mappedBy = "cart")
    private List<ProductCount> productCounts = new ArrayList<>();

}
