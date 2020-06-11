package pl.zielinska.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.zielinska.model.domain.Coordinates;
import pl.zielinska.model.domain.CustomLocation;

import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
public class CoordinatesUtil {

    public static ResponseEntity<String> getResponseFor(String city, String street) {
        RestTemplate restTemplate = new RestTemplate();
        URI targetUrl = UriComponentsBuilder.fromUriString("https://nominatim.openstreetmap.org")
                .path("search")
                .queryParam("q", city + "+" + street)
                .queryParam("format", "geojson")
                .encode(Charset.forName("UTF8"))
                .build()
                .toUri();

        return restTemplate.getForEntity(
                targetUrl,
                String.class);
    }

    public static Coordinates translateAdressToCoordinates(String city, String street) throws JsonProcessingException {
        ResponseEntity<String> response = CoordinatesUtil.getResponseFor(city, street);
        log.debug("Translate adress to coordinates for {}, {}", city, street);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode geometryNode = mapper
                .readTree(response.getBody())
                .at("/features")
                .get(0)
                .at("/geometry/coordinates");

        return Coordinates.builder()
                .x(geometryNode.get(0).doubleValue())
                .y(geometryNode.get(1).doubleValue())
                .build();
    }
}
