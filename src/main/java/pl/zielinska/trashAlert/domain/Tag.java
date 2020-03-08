package pl.zielinska.trashAlert.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tags")
@Data @NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<Ad> ads = new HashSet<>();
}
