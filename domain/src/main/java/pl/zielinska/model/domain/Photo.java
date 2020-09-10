package pl.zielinska.model.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

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

    public static void process(MultipartFile[] files, Ad ad) throws IOException {
        for(MultipartFile file : files) {
            if (file.getSize() != 0) {
                Photo photo = Photo.builder()
                        .photo(file.getBytes())
                        .build();
                ad.addPhoto(photo);
            }
        }
    }
}
