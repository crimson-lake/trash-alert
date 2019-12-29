package pl.zielinska.trashAlert.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "photo")
    private byte[] photo;

    @NonNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ad_id", nullable=false)
    private int adId;

}
