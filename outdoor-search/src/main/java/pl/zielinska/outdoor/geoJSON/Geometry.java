package pl.zielinska.outdoor.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zielinska.outdoor.domain.Coordinates;

@Data @NoArgsConstructor
public class Geometry {

    @JsonProperty("type")
    private String type = "Point";

    @JsonProperty("coordinates")
    private double[] coordinates;

    public Geometry(Coordinates xy) {
        coordinates = new double[]{xy.getX(), xy.getY()};
    }
}
