package pl.zielinska.outdoor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pl.zielinska.model.domain.City;
import pl.zielinska.outdoor.util.Regex;
import pl.zielinska.outdoor.validation.ConfirmedPassword;
import pl.zielinska.outdoor.validation.UniqueEmail;
import pl.zielinska.outdoor.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfirmedPassword
public class UserDto implements Dto{

    @NotBlank
    @Length(min=6, max=20)
    @UniqueUsername
    private String username;

    @NotBlank
    @Pattern(regexp = Regex.LETTERS_ONLY, message = "Should consist only of letters")
    private String firstName;

    @NotBlank
    @Pattern(regexp = Regex.LETTERS_ONLY, message = "Should consist only of letters")
    private String lastName;

    @NotBlank
    @Length(min=6, max=20)
    @JsonIgnore
    private String password;

    @NotBlank
    @Length(min=6, max=20)
    @JsonIgnore
    private String confirmPassword;

    private City defaultCity;

    @Email
    @NotBlank
    @UniqueEmail
    @Pattern(regexp = Regex.EMAIL, message = "Email not valid")
    private String email;

    @JsonIgnore
    private String recaptcha;
}
