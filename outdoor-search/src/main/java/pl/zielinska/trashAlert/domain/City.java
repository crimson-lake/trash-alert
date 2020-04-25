package pl.zielinska.trashAlert.domain;

import lombok.Getter;

@Getter
public enum City {
    ALL(19.08, 51.55, 6, "city.all"),
    KRAKOW(19.93,50.06, 12, "city.cracow"),
    POZNAN(16.93, 52.41, 12, "city.poznan"),
    WARSAW(21.00, 52.23, 12, "city.warsaw"),
    WROCLAW(17.03, 51.10, 12, "city.wroclaw"),
    TRICITY(18.59,54.44, 11, "city.3city");

    private double x;
    private double y;
    private int zoom;
    private String name;

    City(double x, double y, int zoom, String name) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
        this.name = name;
    }
}
