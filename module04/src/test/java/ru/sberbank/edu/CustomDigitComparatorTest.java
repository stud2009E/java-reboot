package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class CustomDigitComparatorTest {

    private final Comparator<Integer> comp = new CustomDigitComparator();

    @Test
    public void comparatorTest0() {
        Integer[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = new ArrayList<>(List.of(data));
        list.sort(comp);

        Integer[] result = new Integer[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
        Assertions.assertArrayEquals(list.toArray(), result);
    }

    @Test
    public void comparatorTest1() {
        Integer[] data = {9, 7, 5, 3, 1, 8, 6, 4, 2, 0};
        List<Integer> list = new ArrayList<>(List.of(data));
        list.sort(comp);

        Integer[] result = new Integer[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
        Assertions.assertArrayEquals(list.toArray(), result);
    }

    @Test
    public void comparatorTest3() {
        Integer[] data = {1, 3, 5, 7, 9, -8, -6, -4, -2, 0};
        List<Integer> list = new ArrayList<>(List.of(data));
        list.sort(comp);

        Integer[] result = new Integer[]{-8, -6, -4, -2, 0, 1, 3, 5, 7, 9};
        Assertions.assertArrayEquals(list.toArray(), result);
    }
}
