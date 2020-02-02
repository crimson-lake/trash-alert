package pl.zielinska.trashAlert.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "enabled")
    private boolean enabled;

    @JsonIgnore
    @Column(name = "authority")
    private String authority;

    @JsonBackReference
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
}
