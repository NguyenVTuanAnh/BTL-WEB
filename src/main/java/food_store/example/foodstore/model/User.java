package food_store.example.foodstore.model;


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

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
