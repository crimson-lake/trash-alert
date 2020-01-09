package pl.zielinska.trashAlert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="ads")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title", "city", "street", "created"})
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private Date created;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User adAuthor;

    @OneToMany( fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "author")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany( fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                mappedBy = "adId")
    private Set<Photo> photos = new HashSet<>();

    @ManyToMany
    @JoinTable( name = "tags_linking_table",
                joinColumns = @JoinColumn(name = "ad_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

}
