package ru.sberbank.edu;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        WeatherProvider provider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(provider);

        long start = System.currentTimeMillis();
        WeatherInfo info = cache.getWeatherInfo("Moscow");
        System.out.println(info);
        System.out.printf("No cache: %d%n", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        info = cache.getWeatherInfo("Moscow");
        System.out.println(info);
        System.out.printf("With cache: %d%n", System.currentTimeMillis() - start);

        List<String> cities = Arrays.asList("Moscow", "London", "Moscow", "London", "Minsk", "Dublin", "Paris", "Moscow", "London", "Moscow", "London");
        Map<String, WeatherInfo> infoMap = Collections.synchronizedMap(new HashMap<>());
        ExecutorService service = Executors.newFixedThreadPool(3);

        for (String city : cities) {
            service.submit(() -> {
                String name = Thread.currentThread().getName();
                System.out.printf("thread %s start with city %s%n", name, city);

                long init = System.currentTimeMillis();
                try {
                    cache.getWeatherInfo(city);
                    System.out.printf("thread %s takes time %d for %s%n", name, System.currentTimeMillis() - init, city);
                } catch (Exception e) {
                    System.out.printf("remote error: thread %s city %s %n", name, city);
                }
            });
        }

        service.shutdown();
    }
}
