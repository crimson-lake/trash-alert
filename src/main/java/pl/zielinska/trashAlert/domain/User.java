package pl.zielinska.trashAlert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.zielinska.trashAlert.DTO.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users_data")
@NoArgsConstructor @AllArgsConstructor @Builder @Data
@EqualsAndHashCode(of = {"id", "username", "firstName", "lastName", "email"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "authority")
    private String authority;

    @OneToMany( fetch=FetchType.LAZY,
                cascade=CascadeType.ALL,
                mappedBy = "adAuthor")
    @Singular private Set<Ad> ads = new HashSet<>();

    @OneToMany( fetch=FetchType.LAZY,
                cascade=CascadeType.ALL,
                mappedBy = "author")
    @Singular private Set<Comment> comments = new HashSet<>();

    public void addNewAd(Ad theAd) {
        ads.add(theAd);
        theAd.setAdAuthor(this);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public UserDto toDto() {
        return UserDto.builder()
                .username(this.username)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .build();
    }
}
