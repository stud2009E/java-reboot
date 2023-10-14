package ru.sberbank.edu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GDCTest {

    private GCD gdc;

    @Before
    public void init(){
        gdc = new GCD();
    }

    @After
    public void clean(){
        gdc = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNegative(){
        Assert.assertEquals(100, gdc.getDivisor(-1, 100));
        Assert.assertEquals(100, gdc.getDivisor(-5, 0));
        Assert.assertEquals(100, gdc.getDivisor(100, -5));
        Assert.assertEquals(100, gdc.getDivisor(0, -1));
        Assert.assertEquals(100, gdc.getDivisor(-10, -5));
    }

    @Test
    public void testEuclid(){
        Assert.assertEquals(10, gdc.getDivisor(0, 10));
        Assert.assertEquals(5, gdc.getDivisor(5, 0));
        Assert.assertEquals(7, gdc.getDivisor(7, 49));
        Assert.assertEquals(1, gdc.getDivisor(1, 87));
        Assert.assertEquals(1, gdc.getDivisor(13, 105));
        Assert.assertEquals(16, gdc.getDivisor(16, 12384));
        Assert.assertEquals(1023, gdc.getDivisor(1023, 2046));
        Assert.assertEquals(16, gdc.getDivisor(256, 16));
        Assert.assertEquals(5, gdc.getDivisor(15, 125));
        Assert.assertEquals(6, gdc.getDivisor(24, 30));
        Assert.assertEquals(8, gdc.getDivisor(24, 32));
    }

}
