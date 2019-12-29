package pl.zielinska.trashAlert.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "city")
    private String city;

    @NonNull
    @Column(name = "street")
    private String street;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private LocalDateTime created;

    @NonNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="users_id", nullable=false)
    private int userId;

    @OneToMany( fetch=FetchType.LAZY,
                mappedBy = "adId")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany( fetch=FetchType.LAZY,
                mappedBy = "adId")
    private Set<Photo> photos = new HashSet<>();

    @ManyToMany
    @JoinTable( name = "tags_linking_table",
                joinColumns = @JoinColumn(name = "ad_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
