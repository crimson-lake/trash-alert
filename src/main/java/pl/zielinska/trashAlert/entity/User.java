package pl.zielinska.trashAlert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username", "firstName", "lastName", "email"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany( fetch=FetchType.LAZY,
                cascade=CascadeType.ALL,
                mappedBy = "adAuthor")
    private Set<Ad> ads = new HashSet<>();

    @OneToMany( fetch=FetchType.LAZY,
                cascade=CascadeType.ALL,
                mappedBy = "author")
    private Set<Comment> comments = new HashSet<>();

    public void addAd(Ad theAd) {
        ads.add(theAd);
        theAd.setAdAuthor(this);
    }

    public void removeAd(Ad theAd) {
        ads.remove(theAd);
        theAd.setAdAuthor(null);
    }
}
