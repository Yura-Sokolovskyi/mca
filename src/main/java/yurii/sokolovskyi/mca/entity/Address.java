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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Street street;

    private Integer houseNumber;

    private Integer flatNumber;

    private String info;

    @OneToOne (mappedBy = "address")
    private Cafe cafe;

    @OneToMany (mappedBy = "address")
    private List<User> users = new ArrayList<>();
}
