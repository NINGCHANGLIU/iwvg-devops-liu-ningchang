package es.upm.miw.devops.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDatabaseTest {

    @Test
    void testConstructorStartsEmpty() {
        UsersDatabase db = new UsersDatabase();
        assertEquals(0, db.size());
        assertTrue(db.findAll().isEmpty());
    }

    @Test
    void testAddAndFindById() {
        UsersDatabase db = new UsersDatabase();
        User user = new User(1, "John", "Doe");

        db.add(user);

        assertEquals(1, db.size());
        assertSame(user, db.findById(1));
        assertNull(db.findById(2));
    }

    @Test
    void testAddNullThrowsException() {
        UsersDatabase db = new UsersDatabase();
        assertThrows(IllegalArgumentException.class, () -> db.add(null));
    }

    @Test
    void testAddDuplicateIdThrowsException() {
        UsersDatabase db = new UsersDatabase();
        db.add(new User(1, "John", "Doe"));

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> db.add(new User(1, "Jane", "Smith"))
        );
        assertTrue(ex.getMessage().toLowerCase().contains("already exists"));
    }

    @Test
    void testFindByIdInvalidThrowsException() {
        UsersDatabase db = new UsersDatabase();
        assertThrows(IllegalArgumentException.class, () -> db.findById(0));
        assertThrows(IllegalArgumentException.class, () -> db.findById(-1));
    }

    @Test
    void testFindAllIsUnmodifiable() {
        UsersDatabase db = new UsersDatabase();
        db.add(new User(1, "John", "Doe"));

        List<User> users = db.findAll();
        assertEquals(1, users.size());
        assertThrows(UnsupportedOperationException.class, () -> users.add(new User(2, "Jane", "Smith")));
    }

    @Test
    void testStream() {
        UsersDatabase db = new UsersDatabase();
        db.add(new User(1, "John", "Doe"));
        db.add(new User(2, "Jane", "Smith"));

        assertEquals(2, db.stream().count());
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 9 tests: findHighestFraction()
    // ---------------------------------------------------------

    @Test
    void testFindHighestFractionWhenNoUsersReturnsNull() {
        UsersDatabase db = new UsersDatabase();
        assertNull(db.findHighestFraction());
    }

    @Test
    void testFindHighestFractionWhenUsersHaveNoFractionsReturnsNull() {
        UsersDatabase db = new UsersDatabase();
        db.add(new User(1, "John", "Doe"));
        db.add(new User(2, "Jane", "Smith"));
        assertNull(db.findHighestFraction());
    }

    @Test
    void testFindHighestFractionReturnsMaxAcrossAllUsers() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        User u2 = new User(2, "Jane", "Smith");

        u1.addFraction(new Fraction(1, 2));   // 0.5
        u1.addFraction(new Fraction(3, 4));   // 0.75
        u2.addFraction(new Fraction(5, 6));   // 0.8333...
        u2.addFraction(new Fraction(7, 10));  // 0.7

        db.add(u1);
        db.add(u2);

        Fraction highest = db.findHighestFraction();
        assertEquals(new Fraction(5, 6), highest);
    }

    @Test
    void testFindHighestFractionSingleUserMultipleFractions() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        u1.addFraction(new Fraction(2, 3));  // 0.666...
        u1.addFraction(new Fraction(9, 10)); // 0.9
        db.add(u1);

        assertEquals(new Fraction(9, 10), db.findHighestFraction());
    }
}
