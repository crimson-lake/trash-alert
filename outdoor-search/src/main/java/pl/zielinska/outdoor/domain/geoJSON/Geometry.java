package pl.zielinska.outdoor.domain.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Geometry {

    @JsonProperty("type")
    private String type;

    @JsonProperty("coordinates")
    private double[] coordinates;
}
