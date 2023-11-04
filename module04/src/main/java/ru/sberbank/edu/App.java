package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Integer[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = new ArrayList<>(List.of(data));
        list.sort(new CustomDigitComparator());

        System.out.println(list);

        Person person = new Person("City", "name", 15);
        System.out.println(person);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Ccc", "aaa", 25));
        personList.add(new Person("Bbb", "ddd", 26));
        personList.add(new Person("Bbb", "zzz", 26));
        personList.add(new Person("Aaa", "ccc", 27));
        personList.add(new Person("Aaa", "ccc", 21));

        personList.sort(Person::compareTo);

        System.out.println(personList);
    }
}
