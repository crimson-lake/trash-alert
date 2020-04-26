package pl.zielinska.outdoor.service;

import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.geoJSON.GeoJSONCollection;

public interface GeoService {
    GeoJSONCollection getCoordinates();
    void adNewCoordinates(Ad theAd);
}
