package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class AppTest {
    @Test
    public void testWeatherInfo(){
        String city = "Moscow";
        double temperature = 20.10;
        double feelTemperature = 21.1;
        String description = "Snow";
        String shortDescription = "Snow snow snow";
        double pressure = 345.5;
        double windSpeed = 12.2;
        LocalDateTime expiryTime = LocalDateTime.of(1980, 10, 17, 23,59,59);

        WeatherInfo weatherInfo = WeatherInfo.getBuilder()
                .setCity(city)
                .setTemperature(temperature)
                .setFeelsLikeTemperature(feelTemperature)
                .setDescription(description)
                .setShortDescription(shortDescription)
                .setPressure(pressure)
                .setWindSpeed(windSpeed)
                .setExpiryTime(expiryTime)
                .build();


        Assertions.assertEquals(city, weatherInfo.city());
        Assertions.assertEquals(temperature, weatherInfo.temperature());
        Assertions.assertEquals(feelTemperature, weatherInfo.feelsLikeTemperature());
        Assertions.assertEquals(description, weatherInfo.description());
        Assertions.assertEquals(shortDescription, weatherInfo.shortDescription());
        Assertions.assertEquals(pressure, weatherInfo.pressure());
        Assertions.assertEquals(windSpeed, weatherInfo.windSpeed());
        Assertions.assertEquals(expiryTime, weatherInfo.expiryTime());
    }

    @Test
    public void testWeatherCache(){
        WeatherInfo weatherInfo = WeatherInfo.getBuilder().setExpiryTime(LocalDateTime.now().plusMinutes(5)).build();

        WeatherProvider provider = Mockito.mock(WeatherProvider.class);
        Mockito.when(provider.get(Mockito.any())).thenReturn(weatherInfo);

        WeatherCache cache =  Mockito.spy(new WeatherCache(provider));

        cache.getWeatherInfo("1");
        cache.getWeatherInfo("2");
        cache.getWeatherInfo("2");
        cache.getWeatherInfo("2");
        cache.getWeatherInfo("3");
        cache.getWeatherInfo("3");

        Mockito.verify(cache, Mockito.times(6)).getWeatherInfo(Mockito.any());
        Mockito.verify(provider, Mockito.times(3)).get(Mockito.any());
    }

    @Test
    public void testWeatherNoCache(){
        WeatherInfo weatherInfo = WeatherInfo.getBuilder().setExpiryTime(LocalDateTime.now()).build();

        WeatherProvider provider = Mockito.mock(WeatherProvider.class);
        Mockito.when(provider.get(Mockito.any())).thenReturn(weatherInfo);

        WeatherCache cache =  Mockito.spy(new WeatherCache(provider));

        cache.getWeatherInfo("1");
        cache.getWeatherInfo("2");
        cache.getWeatherInfo("2");
        cache.getWeatherInfo("2");
        cache.getWeatherInfo("3");
        cache.getWeatherInfo("3");

        Mockito.verify(cache, Mockito.times(6)).getWeatherInfo(Mockito.any());
        Mockito.verify(provider, Mockito.times(6)).get(Mockito.any());
    }
}