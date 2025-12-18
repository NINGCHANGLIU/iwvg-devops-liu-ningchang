package es.upm.miw.devops.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {

    @Test
    void testConstructorAndGetters() {
        Fraction fraction = new Fraction(1, 2);
        assertEquals(1, fraction.getNumerator());
        assertEquals(2, fraction.getDenominator());
    }

    @Test
    void testConstructorDenominatorZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Fraction(1, 0)
        );
        assertTrue(ex.getMessage().toLowerCase().contains("denominator"));
    }

    @Test
    void testToString() {
        Fraction fraction = new Fraction(3, 4);
        assertEquals("3/4", fraction.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(1, 2);
        Fraction c = new Fraction(2, 3);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void testIsProper() {
        assertTrue(new Fraction(1, 2).isProper());
        assertTrue(new Fraction(-1, 2).isProper());
        assertTrue(new Fraction(0, 5).isProper());
        assertFalse(new Fraction(2, 2).isProper());
        assertFalse(new Fraction(3, 2).isProper());
    }

    @Test
    void testIsImproper() {
        assertFalse(new Fraction(1, 2).isImproper());
        assertTrue(new Fraction(2, 2).isImproper());
        assertTrue(new Fraction(3, 2).isImproper());
        assertTrue(new Fraction(-3, 2).isImproper());
    }

    @Test
    void testIsEquivalent() {
        assertTrue(new Fraction(1, 2).isEquivalent(new Fraction(2, 4)));
        assertTrue(new Fraction(-1, 2).isEquivalent(new Fraction(2, -4)));
        assertFalse(new Fraction(1, 2).isEquivalent(new Fraction(1, 3)));
    }

    @Test
    void testIsEquivalentNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Fraction(1, 2).isEquivalent(null)
        );
    }
}
