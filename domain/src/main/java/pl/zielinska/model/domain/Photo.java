package pl.zielinska.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="photos")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = {"id", "photo"})
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ad_id", nullable=false)
    private Ad ad;
}
