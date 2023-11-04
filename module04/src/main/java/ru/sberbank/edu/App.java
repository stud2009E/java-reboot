package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        Integer[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = new ArrayList<>(List.of(data));
        list.sort(new CustomDigitComparator());

        System.out.println(list);
    }
}
