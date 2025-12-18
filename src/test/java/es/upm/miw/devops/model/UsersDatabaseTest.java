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
}
