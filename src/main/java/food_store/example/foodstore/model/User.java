package food_store.example.foodstore.model;


import food_store.example.foodstore.constant.ProviderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String phone;
    private String fullname;
    private String address;
    private String urlImage;
    private String code;
    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
