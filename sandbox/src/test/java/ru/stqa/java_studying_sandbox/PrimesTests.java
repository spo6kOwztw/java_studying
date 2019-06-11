package ru.stqa.java_studying_sandbox;

import org.testng.annotations.Test;
import org.testng.Assert;
import ru.stqa.java_studying.sandbox.Primes;

public class PrimesTests {
    @Test
    public void testPrimeFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test(enabled =  false)
    public void testPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testNonPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
