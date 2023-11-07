package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void eqTest() {
        Person bob = new Person("Moscow", "Bob", 20);
        Person tom = new Person("Paris", "Tom", 21);

        Person bobUpper = new Person("Moscow".toUpperCase(), "Bob".toUpperCase(), 20);
        Person tomLower = new Person("Paris".toLowerCase(), "Tom".toLowerCase(), 21);

        Assertions.assertNotEquals(bob, tom);
        Assertions.assertNotEquals(tom.hashCode(), bob.hashCode());

        Assertions.assertEquals(bob, bob);
        Assertions.assertEquals(tom, tom);

        Assertions.assertEquals(bob, bobUpper);
        Assertions.assertEquals(bob.hashCode(), bobUpper.hashCode());

        Assertions.assertEquals(tom, tomLower);
        Assertions.assertEquals(tom.hashCode(), tomLower.hashCode());
    }

    @Test
    public void compareTest(){
        Person a = new Person("London", "Bob", 30);
        Person b = new Person("Moscow", "Alice", 18);
        Assertions.assertTrue(a.compareTo(b) < 0);
        Assertions.assertEquals(0, a.compareTo(a));

        Person c = new Person("York", "Bob", 26);
        Assertions.assertTrue(a.compareTo(c) < 0);

        Person d = new Person("London", "Alice", 19);
        Assertions.assertTrue(a.compareTo(d) > 0);

        Person e = new Person("London", "Bob", 19);
        Assertions.assertTrue(a.compareTo(e) > 0);

        Person f = new Person("London".toLowerCase(), "Bob".toLowerCase(), 19);
        Assertions.assertTrue(a.compareTo(f) < 0);
    }

}
