package org.example;
import org.testng.Assert;
import org.testng.annotations.Test;
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

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Число должно быть неотрицательным")
    public void testFactorialNegativeNumber() {
        factorial(-1);
    }
}
