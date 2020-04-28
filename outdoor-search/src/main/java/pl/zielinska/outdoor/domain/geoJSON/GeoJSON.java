package pl.zielinska.outdoor.domain.geoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.util.CoordinatesUtil;

import java.net.URI;
import java.nio.charset.Charset;
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

    public GeoJSON(Ad ad) throws JsonProcessingException {
        this.properties.put("title", ad.getTitle());
        this.properties.put("id", "" + ad.getId());
        this.properties.put("address", ad.getStreet() + ", " + ad.getCity());

        ResponseEntity<String> response = CoordinatesUtil.getResponseFor(ad.getCity(), ad.getStreet());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode geometryNode = mapper
                .readTree(response.getBody())
                .at("/features")
                .get(0)
                .at("/geometry");
        geometry = mapper.treeToValue(geometryNode, Geometry.class);

        log.info(geometry.toString());
    }

}
