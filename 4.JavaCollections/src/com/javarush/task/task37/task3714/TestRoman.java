package com.javarush.task.task37.task3714;

import org.junit.Assert;
import org.junit.Test;

public class TestRoman {
    @Test
    public void testRomanToInteger() {
        Assert.assertEquals(1000, Solution.romanToInteger("M"));
        Assert.assertEquals(3999, Solution.romanToInteger("MMMCMXCIX"));
    }
}
