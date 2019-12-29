package pl.zielinska.trashAlert.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "email")
    private String email;

    @OneToMany( fetch=FetchType.LAZY,
                mappedBy = "userId")
    private Set<Ad> ads = new HashSet<>();

    @OneToMany( fetch=FetchType.LAZY,
            mappedBy = "userId")
    private Set<Comment> comments = new HashSet<>();
}
