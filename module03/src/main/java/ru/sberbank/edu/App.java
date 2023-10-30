package ru.sberbank.edu;

public class App
{
    public static void main( String[] args ) {
        CustomArray<String> customArray = new CustomArrayImpl<>();

        customArray.add("Ivan");
        customArray.add("Pavel");
        customArray.add("Bob");
        customArray.add("Tom");

        System.out.println(customArray);
        System.out.println("removed item = " + customArray.remove(1));

        System.out.println(customArray);
    }
}
