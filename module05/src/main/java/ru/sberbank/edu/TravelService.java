package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public boolean add(CityInfo cityInfo) {
        boolean exist = cities.stream()
                .map(CityInfo::name)
                .anyMatch(name -> name.equals(cityInfo.name()));

        if (exist) {
            throw new IllegalArgumentException(String.format("city %s already exists!", cityInfo.name()));
        }

        return cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        cities.stream()
                .map(CityInfo::name)
                .filter(name -> name.equals(cityName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("city %s does not exist!", cityName)));

        cities.removeIf(cityInfo -> cityInfo.name().equals(cityName));
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream().map(CityInfo::name).collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        GeoPosition src = cityByName(srcCityName).position();
        GeoPosition dest = cityByName(destCityName).position();

        return getDistance(src, dest);
    }

    private int getDistance(GeoPosition src, GeoPosition dest) {
        return 0;
    }

    private CityInfo cityByName(String cityName) {
        return cities.stream()
                .filter(cityInfo -> cityInfo.name().equals(cityName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can not find city: %s", cityName)));
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {

        return cities.stream()
                .map(CityInfo::name)
                .filter(name -> getDistance(cityName, name) > radius)
                .collect(Collectors.toList());
    }
}
