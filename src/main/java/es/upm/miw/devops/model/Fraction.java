package es.upm.miw.devops.model;

import java.util.Objects;

public class Fraction {

    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    /**
     * Proper fraction: |numerator| < |denominator|
     */
    public boolean isProper() {
        return Math.abs(this.numerator) < Math.abs(this.denominator);
    }

    /**
     * Improper fraction: |numerator| >= |denominator|
     */
    public boolean isImproper() {
        return Math.abs(this.numerator) >= Math.abs(this.denominator);
    }

    /**
     * Two fractions are equivalent if a/b == c/d  <=>  a*d == c*b
     */
    public boolean isEquivalent(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException("Other fraction must not be null");
        }
        return (long) this.numerator * (long) other.denominator
                == (long) other.numerator * (long) this.denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fraction)) return false;
        Fraction other = (Fraction) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
