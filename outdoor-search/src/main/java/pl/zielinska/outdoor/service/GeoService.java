package pl.zielinska.outdoor.service;

import pl.zielinska.outdoor.domain.geoJSON.GeoJSONCollection;

public interface GeoService {
    GeoJSONCollection getCoordinates();
}
