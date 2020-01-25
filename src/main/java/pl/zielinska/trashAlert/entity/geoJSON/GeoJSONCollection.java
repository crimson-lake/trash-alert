package pl.zielinska.trashAlert.entity.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zielinska.trashAlert.entity.Ad;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class GeoJSONCollection {

    @JsonProperty("type")
    private String type = "FeatureCollection";

    @JsonProperty("features")
    private List<GeoJSON> features = new ArrayList<>();

    public void addFeature(Ad ad) throws JsonProcessingException {
        features.add(new GeoJSON(ad));
    }
}
