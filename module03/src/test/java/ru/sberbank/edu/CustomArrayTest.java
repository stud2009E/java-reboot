package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomArrayTest {

    @Test
    public void constructorTest(){
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
        }catch ( IllegalArgumentException e ){
            Assertions.assertNotNull(e);
        }

        Assertions.assertTrue(strings.isEmpty());
        Assertions.assertTrue(obj10.isEmpty());
        Assertions.assertTrue(ints5.isEmpty());
        Assertions.assertTrue(ints0.isEmpty());
    }

    @Test
    public void addTest(){
        CustomArray<String> strings = new CustomArrayImpl<>();
        strings.add("Tom");
        strings.add("John");
        strings.add("Bob");

        Assertions.assertEquals(strings.size(), 3);
        Assertions.assertFalse(strings.isEmpty());
    }

}