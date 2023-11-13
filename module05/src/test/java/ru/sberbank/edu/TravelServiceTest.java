package ru.sberbank.edu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TravelServiceTest {

    GeoPosition moscowGeo;
    GeoPosition sochiGeo;
    GeoPosition kazanGeo;
    GeoPosition londonGeo;
    GeoPosition newYorkGeo;

    CityInfo moscow;
    CityInfo sochi;
    CityInfo kazan;
    CityInfo london;
    CityInfo newYork;

    @BeforeEach
    public void setUp() {
        moscowGeo = new GeoPosition("55(45'07'')", "37(36'56'')");
        sochiGeo = new GeoPosition("43(35'57'')", "39(43'32'')");
        kazanGeo = new GeoPosition("55(47'19'')", "49(07'19'')");
        londonGeo = new GeoPosition("51(30'30'')", "0(07'32'')");
        newYorkGeo = new GeoPosition("43(00'00'')", "-75(00'00'')");

        moscow = new CityInfo("Moscow", moscowGeo);
        sochi = new CityInfo("Sochi", sochiGeo);
        kazan = new CityInfo("Kazan", kazanGeo);
        london = new CityInfo("London", londonGeo);
        newYork = new CityInfo("New-York", newYorkGeo);
    }

    @Test
    public void addTest() {
        TravelService travelService = new TravelService();

        Assertions.assertTrue(travelService.add(moscow));
        Assertions.assertTrue(travelService.add(sochi));
        Assertions.assertTrue(travelService.add(kazan));

        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.add(moscow));
        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.add(sochi));
        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.add(kazan));
    }

    @Test
    public void removeTest() {
        TravelService travelService = new TravelService();

        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.remove(moscow.name()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.remove(sochi.name()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.remove(kazan.name()));

        travelService.add(moscow);
        travelService.add(kazan);
        travelService.add(sochi);

        try {
            travelService.remove(moscow.name());
            travelService.remove(kazan.name());
            travelService.remove(sochi.name());
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }

        try {
            travelService.remove(moscow.name());
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    public void cityNamesTest() {
        TravelService travelService = new TravelService();
        travelService.add(moscow);

        ArrayList<String> cityNames = new ArrayList<>(Collections.singletonList(moscow.name()));
        Assertions.assertEquals(travelService.citiesNames(), cityNames);

        travelService.add(kazan);
        cityNames = new ArrayList<>(Arrays.asList(moscow.name(), kazan.name()));
        Assertions.assertEquals(travelService.citiesNames(), cityNames);

        travelService.add(sochi);
        cityNames = new ArrayList<>(Arrays.asList(moscow.name(), kazan.name(), sochi.name()));
        Assertions.assertEquals(travelService.citiesNames(), cityNames);

        travelService.remove(sochi.name());
        cityNames = new ArrayList<>(Arrays.asList(moscow.name(), kazan.name()));
        Assertions.assertEquals(travelService.citiesNames(), cityNames);
    }

    @Test
    public void distanceTest(){
        final int ERROR = 15;
        final int MOSCOW_SOCHI = 1360;
        final int MOSCOW_KAZAN = 723;
        final int SOCHI_KAZAN = 1512;
        final int MOSCOW_LONDON = 2500;
        final int MOSCOW_NEW_YORK = 7370;
        final int LONDON_NEW_YORK = 5507;

        TravelService travelService = new TravelService();
        travelService.add(moscow);
        travelService.add(kazan);
        travelService.add(sochi);
        travelService.add(london);
        travelService.add(newYork);

        Assertions.assertTrue(travelService.getDistance(moscow.name(), sochi.name()) - MOSCOW_SOCHI < ERROR);
        Assertions.assertTrue(travelService.getDistance(moscow.name(), kazan.name()) - MOSCOW_KAZAN < ERROR);
        Assertions.assertTrue(travelService.getDistance(sochi.name(), kazan.name()) - SOCHI_KAZAN < ERROR);
        Assertions.assertTrue(travelService.getDistance(moscow.name(), london.name()) - MOSCOW_LONDON < ERROR);
        Assertions.assertTrue(travelService.getDistance(moscow.name(), newYork.name()) - MOSCOW_NEW_YORK < ERROR);
        Assertions.assertTrue(travelService.getDistance(london.name(), newYork.name()) - LONDON_NEW_YORK < ERROR);
    }


    @AfterEach
    public void tearDown() {
        moscowGeo = null;
        sochiGeo = null;
        kazanGeo = null;
        london = null;
        newYork = null;

        moscow = null;
        sochi = null;
        kazan = null;
        londonGeo = null;
        newYorkGeo = null;
    }
}