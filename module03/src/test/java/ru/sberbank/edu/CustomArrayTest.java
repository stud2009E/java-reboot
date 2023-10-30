package ru.sberbank.edu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayTest {

    private final String[] strings10 = new String[]{"Tom", "Ivan", "Boris", "Jim", "Aleks"};
    private final String[] strings2 = new String[]{"Sam", "Michael"};
    private final String[] strings0 = new String[]{};
    private final Integer[] ints10 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void constructorTest() {
        CustomArray<String> strings = new CustomArrayImpl<>();
        CustomArray<Object> obj10 = new CustomArrayImpl<>(15);
        CustomArray<Integer> ints5 = new CustomArrayImpl<>(5);
        CustomArray<Number> ints0 = new CustomArrayImpl<>(0);

        Assertions.assertEquals(strings.size(), 0);
        Assertions.assertEquals(strings.getCapacity(), 10);

        Assertions.assertEquals(obj10.size(), 0);
        Assertions.assertEquals(obj10.getCapacity(), 15);
        Assertions.assertEquals(ints5.getCapacity(), 5);
        Assertions.assertEquals(ints0.size(), 0);

        try {
            new CustomArrayImpl<Number>(-10);
            Assertions.fail("Expected exception was thrown");
        } catch (IllegalArgumentException e) {
            Assertions.assertNotNull(e);
        }

        Assertions.assertTrue(strings.isEmpty());
        Assertions.assertTrue(obj10.isEmpty());
        Assertions.assertTrue(ints5.isEmpty());
        Assertions.assertTrue(ints0.isEmpty());
    }

    @Test
    public void addTest() {
        CustomArray<String> strings = new CustomArrayImpl<>();
        strings.add("Tom");
        strings.add("John");
        strings.add("Bob");

        Assertions.assertEquals(strings.size(), 3);
        Assertions.assertFalse(strings.isEmpty());
    }

    @Test
    public void addWithGrowTest() {
        CustomArray<String> strings = new CustomArrayImpl<>(3);

        strings.add("Tom");
        Assertions.assertEquals(strings.size(), 1);
        Assertions.assertFalse(strings.isEmpty());

        strings.add("John");
        Assertions.assertEquals(strings.size(), 2);
        Assertions.assertFalse(strings.isEmpty());

        strings.add("Bob");
        Assertions.assertEquals(strings.size(), 3);
        Assertions.assertFalse(strings.isEmpty());
        Assertions.assertEquals(strings.getCapacity(), 0);

        strings.add("James");
        Assertions.assertEquals(strings.size(), 4);
        Assertions.assertFalse(strings.isEmpty());

        strings.add("Ivan");
        Assertions.assertEquals(strings.size(), 5);
        Assertions.assertFalse(strings.isEmpty());
        Assertions.assertTrue(strings.getCapacity() > 0);
    }

    @Test
    public void addAllTest() {
        CustomArray<Integer> intArray1 = new CustomArrayImpl<>();

        Assertions.assertTrue(intArray1.addAll(ints10));
        Assertions.assertEquals(ints10.length, intArray1.size());

        CustomArray<String> stringArray = new CustomArrayImpl<>();
        Assertions.assertFalse(stringArray.addAll(strings0));

        CustomArray<Integer> intArray2 = new CustomArrayImpl<>();
        List<Integer> integerList = new ArrayList<>(List.of(ints10));
        Assertions.assertTrue(intArray2.addAll(integerList));
        Assertions.assertEquals(intArray2.size(), integerList.size());

        CustomArray<String> stringArray2 = new CustomArrayImpl<>();
        Assertions.assertTrue(stringArray2.addAll(strings10));
        Assertions.assertTrue(stringArray2.addAll(2, strings2));
        Assertions.assertEquals(stringArray2.size(), strings10.length + strings2.length);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> stringArray2.addAll(-1, strings2));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> stringArray2.addAll(stringArray2.size() + 1, strings2));
    }

}