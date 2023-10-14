package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( new GreetingImpl("Tom", "programming"));
        System.out.println(new GreetingImpl(null,null));
    }
}
