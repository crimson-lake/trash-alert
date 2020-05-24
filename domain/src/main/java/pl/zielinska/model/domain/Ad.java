package pl.zielinska.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;
import pl.zielinska.model.util.CoordinatesUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="ads")
@NoArgsConstructor @AllArgsConstructor @Builder @Data
@EqualsAndHashCode(of = {"id", "title", "city", "street", "created"})
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

    private LocalDateTime created;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToOne(  fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinColumn(name="coordinates_id")
    private Coordinates coordinates;

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return created.format(formatter);
    }

    public Coordinates getCoordinates() {
        if (coordinates == null) {
            try {
                coordinates = CoordinatesUtil.translateAdressToCoordinates(city, street);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return coordinates;
    }

    public void setAdAuthor(User theUser) {
        this.adAuthor = theUser;
        if (!theUser.getAds().contains(this)) {
            theUser.addNewAd(this);
        }
    }
}
