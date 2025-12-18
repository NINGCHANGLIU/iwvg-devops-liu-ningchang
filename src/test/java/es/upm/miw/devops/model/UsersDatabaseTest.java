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
        assertNotNull(highest);
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

    @Test
    void testFindHighestFractionHandlesEquivalentFractionsKeepsAValidMax() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        User u2 = new User(2, "Jane", "Smith");

        u1.addFraction(new Fraction(1, 2));  // 0.5
        u2.addFraction(new Fraction(2, 4));  // 0.5 (equivalent)

        db.add(u1);
        db.add(u2);

        Fraction highest = db.findHighestFraction();
        assertNotNull(highest);
        // Accept either equivalent representation as max; both are valid highest values.
        assertTrue(highest.equals(new Fraction(1, 2)) || highest.equals(new Fraction(2, 4)));
    }

    @Test
    void testFindHighestFractionWithNegativeAndZeroValues() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        User u2 = new User(2, "Jane", "Smith");

        u1.addFraction(new Fraction(-1, 2)); // -0.5
        u1.addFraction(new Fraction(0, 5));  // 0
        u2.addFraction(new Fraction(1, 3));  // 0.333...

        db.add(u1);
        db.add(u2);

        assertEquals(new Fraction(1, 3), db.findHighestFraction());
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 4 tests: findFirstDecimalFractionByUserName()
    // ---------------------------------------------------------

    @Test
    void testFindFirstDecimalFractionByUserNameNullNameThrowsException() {
        UsersDatabase db = new UsersDatabase();
        assertThrows(IllegalArgumentException.class, () -> db.findFirstDecimalFractionByUserName(null));
    }

    @Test
    void testFindFirstDecimalFractionByUserNameBlankNameThrowsException() {
        UsersDatabase db = new UsersDatabase();
        assertThrows(IllegalArgumentException.class, () -> db.findFirstDecimalFractionByUserName("  "));
    }

    @Test
    void testFindFirstDecimalFractionByUserNameWhenUserNotFoundReturnsNull() {
        UsersDatabase db = new UsersDatabase();
        db.add(new User(1, "John", "Doe"));
        assertNull(db.findFirstDecimalFractionByUserName("Alice"));
    }

    @Test
    void testFindFirstDecimalFractionByUserNameWhenUserHasNoFractionsReturnsNull() {
        UsersDatabase db = new UsersDatabase();
        db.add(new User(1, "John", "Doe"));
        assertNull(db.findFirstDecimalFractionByUserName("John"));
    }

    @Test
    void testFindFirstDecimalFractionByUserNameReturnsFirstFractionDecimalValue() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        u1.addFraction(new Fraction(1, 2));   // first -> 0.5
        u1.addFraction(new Fraction(3, 4));   // second -> 0.75

        db.add(u1);

        Double result = db.findFirstDecimalFractionByUserName("John");
        assertNotNull(result);
        assertEquals(0.5, result, 1e-9);
    }

    @Test
    void testFindFirstDecimalFractionByUserNameWorksWithNegativeValues() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        u1.addFraction(new Fraction(-1, 2)); // -0.5

        db.add(u1);

        Double result = db.findFirstDecimalFractionByUserName("John");
        assertNotNull(result);
        assertEquals(-0.5, result, 1e-9);
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 8 tests: findUserFamilyNameBySomeImproperFraction()
    // ---------------------------------------------------------

    @Test
    void testFindUserFamilyNameBySomeImproperFractionWhenNoUsersReturnsNull() {
        UsersDatabase db = new UsersDatabase();
        assertNull(db.findUserFamilyNameBySomeImproperFraction());
    }

    @Test
    void testFindUserFamilyNameBySomeImproperFractionWhenNoImproperFractionsReturnsNull() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        u1.addFraction(new Fraction(1, 2)); // proper
        db.add(u1);

        assertNull(db.findUserFamilyNameBySomeImproperFraction());
    }

    @Test
    void testFindUserFamilyNameBySomeImproperFractionReturnsFamilyName() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        u1.addFraction(new Fraction(1, 2)); // proper
        db.add(u1);

        User u2 = new User(2, "Jane", "Smith");
        u2.addFraction(new Fraction(3, 2)); // improper
        db.add(u2);

        // Only u2 has improper, so the result must be "Smith"
        assertEquals("Smith", db.findUserFamilyNameBySomeImproperFraction());
    }

    @Test
    void testFindUserFamilyNameBySomeImproperFractionCountsEqualAsImproper() {
        UsersDatabase db = new UsersDatabase();

        User u1 = new User(1, "John", "Doe");
        u1.addFraction(new Fraction(2, 2)); // |num| == |den| => improper
        db.add(u1);

        assertEquals("Doe", db.findUserFamilyNameBySomeImproperFraction());
    }
}
