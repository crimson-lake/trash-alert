package pl.zielinska.outdoor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pl.zielinska.outdoor.domain.City;
import pl.zielinska.outdoor.validation.ConfirmedPassword;
import pl.zielinska.outdoor.validation.UniqueEmail;
import pl.zielinska.outdoor.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfirmedPassword
public class UserDto {

    @NotBlank
    @Length(min=6, max=20)
    @UniqueUsername
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
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
    private String email;

    @JsonIgnore
    private String recaptcha;
}
