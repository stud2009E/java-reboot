package ru.sberbank.edu;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Geo position.
 */
public class GeoPosition {

    private static final String SHORT_PATTERN = "^(-?)(\\d{1,3})$";
    private static final String LONG_PATTERN = "^(-?)(\\d{1,3})\\((\\d{2})'(\\d{2})''\\)$";
    private static final Pattern longPattern = Pattern.compile(LONG_PATTERN);
    private static final Pattern shortPattern = Pattern.compile(SHORT_PATTERN);
    private final double latitude;
    private final double longitude;

    /**
     * Ctor.
     *
     * @param latitudeDegree  - latitude in degrees
     * @param longitudeDegree - longitude in degrees
     * Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeDegree, String longitudeDegree) {
        // parse and set latitude and longitude in radian
        DegreeWrapper latitudeWrapper = parseDegrees(latitudeDegree);
        latitudeWrapper.checkBorder(90);
        latitude = latitudeWrapper.getRadians();

        DegreeWrapper longitudeWrapper = parseDegrees(longitudeDegree);
        longitudeWrapper.checkBorder(180);
        longitude = longitudeWrapper.getRadians();
    }

    /**
     * get latitude
     * @return {double} latitude
     */
    public double latitude() {
        return latitude;
    }

    /**
     * get longitude
     * @return {double} longitude
     */
    public double longitude() {
        return longitude;
    }

    /**
     * Parse degrees to radian and check
     * @param degreeString degree in strings
     * @return {DegreeWrapper} wrapper for degrees
     */
    private DegreeWrapper parseDegrees(String degreeString) {
        Matcher matcher = longPattern.matcher(degreeString);
        if (!matcher.find()) {
            matcher = shortPattern.matcher(degreeString);

            if (!matcher.find()) {
                throw new IllegalArgumentException("incorrect format");
            }
        }

        String sign = matcher.group(1);
        String degree = matcher.group(2);
        String minute, second;
        try {
            minute = matcher.group(3);
        } catch (IllegalStateException | IndexOutOfBoundsException e) {
            minute = "00";
        }

        try {
            second = matcher.group(4);
        } catch (IllegalStateException | IndexOutOfBoundsException e) {
            second = "00";
        }

        return new DegreeWrapper(sign, degree, minute, second);
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    /**
     * helper class with checks and calculation radians
     * from degrees
     */
    private static class DegreeWrapper {
        final double sign;
        final double degree;
        final double minute;
        final double second;

        DegreeWrapper(String sign, String degree, String minute, String second) {
            try {
                this.sign = Objects.equals(sign, "-") ? -1 : 1;
                this.degree = Double.parseDouble(degree);
                this.minute = Double.parseDouble(minute);
                this.second = Double.parseDouble(second);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
        }

        /**
         * check longitude and latitude on maximum value
         * @param border {double}
         */
        void checkBorder(double border) {
            if (degree > border) {
                throw new IllegalArgumentException();
            }

            if (degree == border && (minute > 0 || second > 0)) {
                throw new IllegalArgumentException();
            }

            if ( minute < 0 || minute > 59 || second < 0 || second > 59) {
                throw new IllegalArgumentException();
            }
        }

        double getRadians() {
            return sign * (degree + (minute + second / 60.0) / 60.0) * Math.PI / 180;
        }

    }
}