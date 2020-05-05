package pl.zielinska.outdoor.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.zielinska.model.domain.Ad;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data @NoArgsConstructor
public class GeoJSON {

    @JsonProperty("type")
    private String type = "Feature";

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    @JsonProperty("geometry")
    private Geometry geometry;

    public GeoJSON(Ad ad) {
        this.properties.put("title", ad.getTitle());
        this.properties.put("id", "" + ad.getId());
        this.properties.put("address", ad.getStreet() + ", " + ad.getCity());

        geometry = new Geometry(ad.getCoordinates());

        log.info(geometry.toString());
    }

}
