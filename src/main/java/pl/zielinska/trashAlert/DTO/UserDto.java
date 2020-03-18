package pl.zielinska.trashAlert.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.zielinska.trashAlert.validation.UniqueEmail;
import pl.zielinska.trashAlert.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank
    @Length(min=6)
    @UniqueUsername
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Length(min=6)
    private String password;

    @Email
    @NotBlank
    @UniqueEmail
    private String email;
}
