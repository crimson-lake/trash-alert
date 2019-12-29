package pl.zielinska.trashAlert.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private LocalDateTime created;

    @NonNull
    @Column(name = "content")
    private String commentContent;

    @NonNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="users_id", nullable=false)
    private int userId;

    @NonNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ad_id", nullable=false)
    private int adId;
}
