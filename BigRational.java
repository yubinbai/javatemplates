/*************************************************************************
 * Compilation: javac BigRational.java
 * Execution: java BigRational
 *
 * ADT for nonnegative Rational numbers. Uses Java's BigInteger
 * library for arbitrary precision. Does not support negative
 * integers or zero.
 *
 * Invariant: all Rational objects are in reduced form (except
 * possibly while modifying).
 *
 *************************************************************************/

import java.math.BigInteger;

public class BigRational {
    private BigInteger num; // the numerator
    private BigInteger den; // the denominator

    // create and initialize a new Rational object
    public BigRational(int numerator, int denominator) {
        // BigInteger constructor takes a string, not an int
        num = new BigInteger(numerator + "");
        den = new BigInteger(denominator + "");
        BigInteger g = num.gcd(den);
        num = num.divide(g);
        den = den.divide(g);
    }

    // create and initialize a new Rational object
    public BigRational(BigInteger numerator, BigInteger denominator) {
        BigInteger g = numerator.gcd(denominator);
        num = numerator.divide(g);
        den = denominator.divide(g);
    }

    // return string representation of (this)
    public String toString() {
        if (den.equals(BigInteger.ONE)) return num + "";
        else return num + "/" + den;
    }

    // return a * b
    public BigRational times(BigRational b) {
        BigRational a = this;
        BigInteger numerator = a.num.multiply(b.num);
        BigInteger denominator = a.den.multiply(b.den);
        return new BigRational(numerator, denominator);
    }

    // return a + b
    public BigRational plus(BigRational b) {
        BigRational a = this;
        BigInteger numerator = a.num.multiply(b.den).add(a.den.multiply(b.num));
        BigInteger denominator = a.den.multiply(b.den);
        return new BigRational(numerator, denominator);
    }

    // return 1 / a
    public BigRational reciprocal() {
        return new BigRational(den, num);
    }

    // return a / b
    public BigRational divides(BigRational b) {
        BigRational a = this;
        return a.times(b.reciprocal());
    }


    /*************************************************************************
     * Computes rational approximation to e using Taylor series
     *
     * e = 1 + 1/1 + 1/2! + 1/3! + ... + 1/N!
     *
     *************************************************************************/

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        BigRational r = new BigRational(1, 1);
        BigRational factorial = new BigRational(1, 1);
        for (int i = 1; i <= N; i++) {
            factorial = factorial.times(new BigRational(i, 1));
            r = r.plus(factorial.reciprocal());
            System.out.println(r);
        }
    }
}
