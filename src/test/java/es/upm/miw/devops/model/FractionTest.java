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
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Fraction(1, 0));
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
}
