package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        WeatherProvider provider = new WeatherProvider();
        WeatherInfo info = provider.get("Moscow");

        System.out.println(info);
    }
}
