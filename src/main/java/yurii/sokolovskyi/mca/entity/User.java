package yurii.sokolovskyi.mca.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String password;

    private String login;

    private Boolean active;

    @OneToMany (mappedBy = "user")
    private List<Invoice> invoices = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Cafe> cafes = new ArrayList<>();

    @OneToMany (mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToOne
    private Cart cart;

    @ManyToMany
    private List<Discount> discounts = new ArrayList<>();

    @OneToOne
    private DiscountCard discountCard;

    @ManyToOne
    private Address address;
}
