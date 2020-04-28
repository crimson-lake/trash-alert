package pl.zielinska.outdoor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="coordinates")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double x;

    private double y;
}
