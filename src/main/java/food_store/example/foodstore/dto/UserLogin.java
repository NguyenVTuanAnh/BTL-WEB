package food_store.example.foodstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    @NotBlank(message = "email must not blank and null")
    @NotNull (message = "email must not blank and null")
    private String email;
    @NotBlank(message = "passwork must not blank and null")
    @NotNull (message = "passwork must not blank and null")
    private String password;

}
