package pl.zielinska.trashAlert.entity.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.zielinska.trashAlert.entity.Ad;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Data @NoArgsConstructor
public class GeoJSON {
    private final static Logger LOGGER = Logger.getLogger("GeoJson");

    @JsonProperty("type")
    private String type = "Feature";

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    @JsonProperty("geometry")
    private Geometry geometry;

    public GeoJSON(Ad ad) throws JsonProcessingException {
        this.properties.put("title", ad.getTitle());
        this.properties.put("link", "/api/ads/" + ad.getId());

        RestTemplate restTemplate = new RestTemplate();
        URI targetUrl= UriComponentsBuilder.fromUriString("https://nominatim.openstreetmap.org")
                .path("search")
                .queryParam("q", ad.getStreet() + "+" + ad.getCity())
                .queryParam("format", "geojson")
                .build()
                .toUri();

        ResponseEntity<String> response = restTemplate.getForEntity(
                        targetUrl,
                        String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode geometryNode = mapper
                .readTree(response.getBody())
                .at("/features")
                .get(0)
                .at("/geometry");
        geometry = mapper.treeToValue(geometryNode, Geometry.class);

        LOGGER.info(geometry.toString());
    }

}
