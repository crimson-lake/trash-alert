package pl.zielinska.outdoor.service;

import pl.zielinska.outdoor.geoJSON.GeoJSONCollection;

public interface GeoService {
    GeoJSONCollection getCoordinates();
}
