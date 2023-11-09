package ru.sberbank.edu;

/**
 * City info
 */
public class CityInfo {

    private final String name;
    private final GeoPosition position;

    /**
     * Ctor.
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    public String name() {
        return name;
    }

    public GeoPosition position() {
        return position;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}