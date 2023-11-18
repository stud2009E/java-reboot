package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String city;
    private final String name;
    private final int age;

    public Person(String city, String name, int age) {
        this.name = Objects.requireNonNull(name);
        this.city = Objects.requireNonNull(city);
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name.toUpperCase(), person.name.toUpperCase()) &&
                Objects.equals(city.toUpperCase(), person.city.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city.toUpperCase(), name.toUpperCase(), age);
    }

    @Override
    public int compareTo(Person other) {
        Objects.requireNonNull(other);

        int cityResult = city.compareTo(other.city);
        if (cityResult != 0){
            return cityResult;
        }

        int nameResult = name.compareTo(other.name);
        if(nameResult != 0){
            return nameResult;
        }

        return age - other.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}