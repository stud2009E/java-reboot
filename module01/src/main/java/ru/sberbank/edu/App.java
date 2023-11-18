package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Greeting tomGreeting = new GreetingImpl("Tom", "programming");
        Greeting anonGreeting = new GreetingImpl(null,null);

        System.out.println(tomGreeting);
        System.out.println(anonGreeting);


        CommonDivisor cmd = new GCD();

        System.out.println(cmd.getDivisor(24, 123));
        System.out.println(cmd.getDivisor(24, 60));
    }
}
