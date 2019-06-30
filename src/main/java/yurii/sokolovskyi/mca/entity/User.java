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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String password;

    private String login;

    @OneToMany (mappedBy = "user")
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany (mappedBy = "user")
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    private List<Cafe> cafes = new ArrayList<>();

    @OneToMany (mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    private Cart cart;


    @ManyToMany
    private List<Discount> discounts = new ArrayList<>();

    @OneToOne
    private DiscountCard discountCard;

    @ManyToOne
    private Address address;
}
