package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeoPositionTest {

    private final double EPSILON = 1e-6;
    private final double EPSILON_MINUTE = 1.0 / (60.0 - EPSILON);
    private final double EPSILON_SECOND = EPSILON_MINUTE / 60.0;

    @Test
    public void argsTest() {
        checkThrow("", "");
        checkThrow("40", "");
        checkThrow("", "45");
        checkThrow("91", "0");
        checkThrow("-91", "0");
        checkThrow("0", "181");
        checkThrow("0", "-181");
        checkThrow("90(00'01'')", "180");
        checkThrow("-90(00'01'')", "180");
        checkThrow("90(01'00'')", "180");
        checkThrow("-90(01'00'')", "180");
        checkThrow("90", "180(00'01'')");
        checkThrow("90", "-180(00'01'')");
        checkThrow("90", "180(01'00'')");
        checkThrow("90", "-180(01'00'')");
        checkThrow("60(60'60'')", "90");
        checkThrow("89(59'61'')", "90");
        checkThrow("90", "90(59'60'')");
        checkThrow("90", "90(61'59'')");
        checkThrow("90(12')", "90");
        checkThrow("90(12'34)", "90");
        checkThrow("90(12'34')", "90");
        checkThrow("90", "90(12')");
        checkThrow("90", "90(12'34)");
        checkThrow("90", "90(12'34')");
    }


    private void checkThrow(String latitude, String longitude) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new GeoPosition(latitude, longitude));
    }


    @Test
    public void equalTest() {
        GeoPosition geo = new GeoPosition("90", "90");
        Assertions.assertEquals(Math.PI / 2, geo.latitude());
        Assertions.assertEquals(Math.PI / 2, geo.longitude());

        GeoPosition geo1 = new GeoPosition("0", "180");
        Assertions.assertEquals(0.0, geo1.latitude());
        Assertions.assertEquals(Math.PI, geo1.longitude());

        GeoPosition geo2 = new GeoPosition("-0", "-180");
        Assertions.assertEquals(-0.0, geo2.latitude());
        Assertions.assertEquals(-Math.PI, geo2.longitude());

        GeoPosition geo3 = new GeoPosition("60", "60");
        Assertions.assertEquals(Math.PI / 3, geo3.latitude());
        Assertions.assertEquals(Math.PI / 3, geo3.longitude());

        GeoPosition geo4 = new GeoPosition("45", "45");
        Assertions.assertEquals(Math.PI / 4, geo4.latitude());
        Assertions.assertEquals(Math.PI / 4, geo4.longitude());

        GeoPosition geo5 = new GeoPosition("30", "30");
        Assertions.assertEquals(Math.PI / 6, geo5.latitude());
        Assertions.assertEquals(Math.PI / 6, geo5.longitude());
    }

    @Test
    public void secondTest() {
        GeoPosition geo1 = new GeoPosition("60(00'01'')", "60(00'01'')");
        Assertions.assertTrue(Math.abs(geo1.latitude() - Math.PI / 3) < EPSILON_SECOND);
        Assertions.assertTrue(Math.abs(geo1.longitude() - Math.PI / 3) < EPSILON_SECOND);

        GeoPosition geo2 = new GeoPosition("45(00'30'')", "45(00'30'')");
        Assertions.assertTrue(Math.abs(geo2.latitude() - Math.PI / 4) < 30 * EPSILON_SECOND);
        Assertions.assertTrue(Math.abs(geo2.longitude() - Math.PI / 4) < 30 * EPSILON_SECOND);

        GeoPosition geo3 = new GeoPosition("30(00'45'')", "30(00'45'')");
        Assertions.assertTrue(Math.abs(geo3.latitude() - Math.PI / 6) < 45 * EPSILON_SECOND);
        Assertions.assertTrue(Math.abs(geo3.longitude() - Math.PI / 6) < 45 * EPSILON_SECOND);
    }

    @Test
    public void minuteTest() {
        GeoPosition geo1 = new GeoPosition("60(01'00'')", "60(01'00'')");
        Assertions.assertTrue(Math.abs(geo1.latitude() - Math.PI / 3) > EPSILON_SECOND
                && Math.abs(geo1.latitude() - Math.PI / 3) < EPSILON_MINUTE);
        Assertions.assertTrue(Math.abs(geo1.longitude() - Math.PI / 3) > EPSILON_SECOND
                && Math.abs(geo1.longitude() - Math.PI / 3) < EPSILON_MINUTE);

        GeoPosition geo2 = new GeoPosition("45(30'00'')", "45(30'00'')");
        Assertions.assertTrue(Math.abs(geo2.latitude() - Math.PI / 4) < 30 * EPSILON_MINUTE);
        Assertions.assertTrue(Math.abs(geo2.longitude() - Math.PI / 4) < 30 * EPSILON_MINUTE);

        GeoPosition geo3 = new GeoPosition("30(45'00'')", "30(45'00'')");
        Assertions.assertTrue(Math.abs(geo3.latitude() - Math.PI / 6) < 45 * EPSILON_MINUTE);
        Assertions.assertTrue(Math.abs(geo3.longitude() - Math.PI / 6) < 45 * EPSILON_MINUTE);
    }

    @Test
    public void minuteSecondTest() {
        GeoPosition geo1 = new GeoPosition("00(01'00'')", "00(01'00'')");
        GeoPosition geo2 = new GeoPosition("00(00'59'')", "00(00'59'')");

        Assertions.assertTrue(Math.abs(geo1.longitude() - geo2.longitude()) < EPSILON_SECOND);
        Assertions.assertTrue(Math.abs(geo1.latitude() - geo2.latitude()) < EPSILON_SECOND);
    }
}
