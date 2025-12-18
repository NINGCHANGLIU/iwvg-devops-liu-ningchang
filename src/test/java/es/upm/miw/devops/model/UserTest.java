package es.upm.miw.devops.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User(1, "Ningchang", "Liu");
        assertEquals(1, user.getId());
        assertEquals("Ningchang", user.getName());
        assertEquals("Liu", user.getFamilyName());
        assertTrue(user.getFractions().isEmpty());
    }

    @Test
    void testConstructorInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> new User(0, "A", "B"));
    }

    @Test
    void testConstructorBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new User(1, "  ", "B"));
    }

    @Test
    void testConstructorBlankFamilyName() {
        assertThrows(IllegalArgumentException.class, () -> new User(1, "A", ""));
    }

    @Test
    void testAddFractionAndGetFractions() {
        User user = new User(1, "A", "B");
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(3, 4);

        user.addFraction(f1);
        user.addFraction(f2);

        List<Fraction> fractions = user.getFractions();
        assertEquals(2, fractions.size());
        assertEquals(f1, fractions.get(0));
        assertEquals(f2, fractions.get(1));
    }

    @Test
    void testGetFractionsIsUnmodifiable() {
        User user = new User(1, "A", "B");
        assertThrows(UnsupportedOperationException.class, () -> user.getFractions().add(new Fraction(1, 2)));
    }

    @Test
    void testAddFractionNull() {
        User user = new User(1, "A", "B");
        assertThrows(IllegalArgumentException.class, () -> user.addFraction(null));
    }

    @Test
    void testEqualsAndHashCodeById() {
        User u1 = new User(1, "A", "B");
        User u2 = new User(1, "X", "Y");
        User u3 = new User(2, "A", "B");

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
        assertNotEquals(u1, u3);
    }
}
