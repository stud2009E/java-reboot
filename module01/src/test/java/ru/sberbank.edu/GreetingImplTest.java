package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.Test;

public class GreetingImplTest {

    @Test
    public void testName(){
        Assert.assertEquals("Name must be Tom", new GreetingImpl("Tom").getName(), "Tom");
        Assert.assertEquals("Name for anonymous", new GreetingImpl(null).getName(), "anonymous");

        Assert.assertEquals("Name must be Bob without hobby", new GreetingImpl("Bob", null).getName(), "Bob");
        Assert.assertEquals("Name must be Tom with hobby", new GreetingImpl("Tom", "swimming").getName(), "Tom");
    }

    @Test
    public void testHobby(){
        Assert.assertEquals("No hobby with name", new GreetingImpl("Tom", null).getBestHobby(), "I have no hobby");
        Assert.assertEquals("No hobby ", new GreetingImpl("Tom").getBestHobby(), "I have no hobby");

        Assert.assertEquals("Hobby must be swimming", new GreetingImpl("Bob", "swimming").getBestHobby(), "My hobby is swimming");
        Assert.assertEquals("Hobby must be swimming", new GreetingImpl(null, "swimming").getBestHobby(), "My hobby is swimming");
    }
    @Test
    public void testGreeting(){
        Assert.assertEquals("Tom without hobby", new GreetingImpl("Tom", null).toString(), "I'm Tom. I have no hobby");
        Assert.assertEquals("Bob without hobby ", new GreetingImpl("Bob").toString(), "I'm Bob. I have no hobby");

        Assert.assertEquals("Tom with hobby swimming", new GreetingImpl("Bob", "swimming").toString(), "I'm Bob. My hobby is swimming");
        Assert.assertEquals("Anonymous with hobby swimming", new GreetingImpl(null, "swimming").toString(), "I'm anonymous. My hobby is swimming");

        Assert.assertEquals("Anonymous with no hobby", new GreetingImpl(null, null).toString(), "I'm anonymous. I have no hobby");
    }

}
