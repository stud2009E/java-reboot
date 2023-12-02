package ru.sberbank.edu;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherProvider {
        private final static String URL = "http://api.openweathermap.org/data/2.5/weather?q={q}&APPID={appId}";
        private final RestTemplate restTemplate = new RestTemplate();
        private static final String APP_ID = "0747854889560766e711233fbdd2e937";

    private static final int EXPIRE_MINUTE = 5;

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appId", APP_ID);

        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class, params);
        JSONObject obj = new JSONObject(response.getBody());

        return parse(obj);
    }

    /**
     * parse response to {@link WeatherInfo}
     * @param obj {{@link JSONObject}} response
     * @return {{@link WeatherInfo}}
     */
    private WeatherInfo parse(JSONObject obj){
        String city = (String) obj.query("/name");
        String shortDescription = (String) obj.query("/weather/0/main");
        String description = (String) obj.query("/weather/0/description");
        BigDecimal temperature =  (BigDecimal)obj.query("/main/temp");
        BigDecimal feelsLikeTemperature = (BigDecimal) obj.query("/main/feels_like");
        BigDecimal windSpeed = (BigDecimal) obj.query("/wind/speed");
        Integer pressure = (Integer) obj.query("/main/pressure");

        return WeatherInfo.getBuilder()
                .setCity(city)
                .setShortDescription(shortDescription)
                .setDescription(description)
                .setTemperature(temperature.doubleValue())
                .setFeelsLikeTemperature(feelsLikeTemperature.doubleValue())
                .setWindSpeed(windSpeed.doubleValue())
                .setPressure(pressure.doubleValue())
                .setExpiryTime(LocalDateTime.now().plusMinutes(EXPIRE_MINUTE))
                .build();
    }
}
