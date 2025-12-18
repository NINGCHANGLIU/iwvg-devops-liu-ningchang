package es.upm.miw.devops.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class UsersDatabase {

    private final Map<Integer, User> usersById;

    public UsersDatabase() {
        this.usersById = new HashMap<>();
    }

    public void add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        Integer id = user.getId();
        if (this.usersById.containsKey(id)) {
            throw new IllegalArgumentException("User id already exists: " + id);
        }
        this.usersById.put(id, user);
    }

    /**
     * Finds a user by id. Returns null if not found.
     */
    public User findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }
        return this.usersById.get(id);
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(this.usersById.values()));
    }

    public int size() {
        return this.usersById.size();
    }

    public Stream<User> stream() {
        return this.usersById.values().stream();
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 9
    // ---------------------------------------------------------
    /**
     * Search 9: findHighestFraction
     *
     * Returns the highest Fraction among all fractions stored in all users.
     * If there are no users or no fractions, returns null.
     */
    public Fraction findHighestFraction() {
        return this.usersById.values().stream()
                .flatMap(user -> user.getFractions().stream())
                .max(Comparator.comparingDouble(f -> (double) f.getNumerator() / f.getDenominator()))
                .orElse(null);
    }
}
