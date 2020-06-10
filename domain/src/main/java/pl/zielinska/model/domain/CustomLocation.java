package pl.zielinska.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="localizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double x;

    private double y;

    public CustomLocation(String name, Coordinates xy) {
        this.x = xy.getX();
        this.y = xy.getY();
        this.name = name;
    }

}
