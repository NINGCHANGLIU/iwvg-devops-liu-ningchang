package es.upm.miw.devops.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
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

        // Add fractions (using reflection to avoid coupling to User API details)
        addFractionToUser(u1, new Fraction(1, 2));   // 0.5
        addFractionToUser(u1, new Fraction(3, 4));   // 0.75
        addFractionToUser(u2, new Fraction(5, 6));   // 0.8333...
        addFractionToUser(u2, new Fraction(7, 10));  // 0.7

        db.add(u1);
        db.add(u2);

        Fraction highest = db.findHighestFraction();
        assertNotNull(highest);
        assertEquals(new Fraction(5, 6), highest);
    }

    // ---------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------

    /**
     * Tries to add a Fraction to a User with minimal assumptions:
     * - addFraction(Fraction)
     * - add(Fraction)
     * - addFraction(int, int)
     */
    private static void addFractionToUser(User user, Fraction fraction) {
        assertNotNull(user);
        assertNotNull(fraction);

        // 1) addFraction(Fraction) or add(Fraction)
        if (tryInvoke(user, "addFraction", new Class<?>[]{Fraction.class}, new Object[]{fraction})) return;
        if (tryInvoke(user, "add", new Class<?>[]{Fraction.class}, new Object[]{fraction})) return;

        // 2) addFraction(int, int)
        if (tryInvoke(user, "addFraction", new Class<?>[]{int.class, int.class},
                new Object[]{fraction.getNumerator(), fraction.getDenominator()})) return;

        fail("No supported method found to add Fraction into User. Expected addFraction(Fraction) / add(Fraction) / addFraction(int,int).");
    }

    private static boolean tryInvoke(Object target, String methodName, Class<?>[] paramTypes, Object[] args) {
        try {
            Method m = target.getClass().getMethod(methodName, paramTypes);
            m.invoke(target, args);
            return true;
        } catch (ReflectiveOperationException ignored) {
            return false;
        }
    }
}
