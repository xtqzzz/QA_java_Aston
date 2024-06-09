package org.example;
import org.junit.Assert;
import org.junit.Test;
import static org.example.Factorial.factorial;


public class FactorialTest {
    @Test
    public void testFactorialPositiveNumber() {
        Assert.assertEquals(120, factorial(5));
    }

    @Test
    public void testFactorialZero() {
        Assert.assertEquals(1, factorial(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativeNumber() {
        factorial(-1);
    }
}