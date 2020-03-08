package pl.zielinska.trashAlert.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import pl.zielinska.trashAlert.validation.Adress;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="ads")
@NoArgsConstructor @AllArgsConstructor @Builder @Data
@EqualsAndHashCode(of = {"id", "title", "city", "street", "created"})
@Adress
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private String details;

    @Temporal(TemporalType.DATE)
    private Date created;

    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User adAuthor;

    @OneToMany( fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "author")
    @Singular private Set<Comment> comments = new HashSet<>();

    @OneToMany( fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "adId")
    @Singular private Set<Photo> photos = new HashSet<>();

    @ManyToMany
    @JoinTable( name = "tags_linking_table",
                joinColumns = @JoinColumn(name = "ad_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Singular private Set<Tag> tags = new HashSet<>();

}
