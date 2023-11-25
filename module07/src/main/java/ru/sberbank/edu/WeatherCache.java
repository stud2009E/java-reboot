package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final WeatherProvider weatherProvider;

    /**
     * Constructor.
     *
     * @param weatherProvider - weather provider
     */
    public WeatherCache(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     * http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=0747854889560766e711233fbdd2e937
     *
     * @param city - city
     * @return actual weather info
     */
    public WeatherInfo getWeatherInfo(String city) {
        boolean isExpired = false;
        WeatherInfo info ;

        synchronized (cache){
            info = cache.get(city);
            if (info != null) {
                isExpired = info.expiryTime().isBefore(LocalDateTime.now());
            }

            if (info == null || isExpired){
                removeWeatherInfo(city);
                try {
                    info = weatherProvider.get(city);
                    cache.put(city, info);
                } catch (Exception ignored){}
            }else {
                System.out.printf("%s with cache for city %s%n", Thread.currentThread().getName(), city);
            }
        }

        return info;
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        synchronized (cache){
            cache.remove(city);
        }
    }
}
