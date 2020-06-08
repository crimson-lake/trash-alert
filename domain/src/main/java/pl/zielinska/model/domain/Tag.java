package pl.zielinska.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tags")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tag")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Ad> ads;

    public void addAd(Ad ad) {
        if (ads == null) {
            ads = new HashSet<>();
        }
        ads.add(ad);
    }
}
