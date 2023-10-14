package ru.sberbank.edu;

/**
 * Greeting of person
 */
public class GreetingImpl implements Greeting{

    private final String name;
    private final String bestHobby;

    /**
     * Greeting constructor
     * @param name name of person
     * @param bestHobby hobby of person
     */
    public GreetingImpl(String name, String bestHobby) {
        this.name = name;
        this.bestHobby = bestHobby;
    }

    public GreetingImpl(String name) {
        this.name = name;
        this.bestHobby = null;
    }



    /**
     * Get the best hobby of person
     * @return hobby
     */
    @Override
    public String getBestHobby() {
        if (bestHobby == null || bestHobby.isEmpty() || bestHobby.isBlank()){
            return "I have no hobby";
        }

        return "My hobby is " + bestHobby;
    }

    /**
     * Get name of person
     * @return name
     */
    public String getName() {
        if (name == null || name.isEmpty() || name.isBlank()){
            return "anonymous";
        }

        return name;
    }

    @Override
    public String toString() {
        return "I'm " + this.getName() + ". " + this.getBestHobby();
    }
}