package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTests {

    @Test
    public void testPrimes() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test(enabled = false)
    public void testPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void isNotPrimeTest() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }

    @Test
    public void isPrimeWhileTest() {
        Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
    }

    @Test
    public void isNotPrimeWhileTest() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
