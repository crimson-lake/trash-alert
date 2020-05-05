package pl.zielinska.outdoor.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zielinska.model.domain.Ad;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class GeoJSONCollection {

    @JsonProperty("type")
    private String type = "FeatureCollection";

    @JsonProperty("features")
    private List<GeoJSON> features = new ArrayList<>();

    public void addFeature(Ad ad) {
        features.add(new GeoJSON(ad));
    }
}
