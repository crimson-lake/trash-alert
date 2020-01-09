package pl.zielinska.trashAlert.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="photos")
@Data @NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ad_id", nullable=false)
    private Ad adId;

}
