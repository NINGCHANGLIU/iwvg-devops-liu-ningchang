package es.upm.miw.devops.model;

import java.util.ArrayList;
import java.util.Collections;
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
                .filter(user -> user != null) // defensive, even if add() blocks null
                .flatMap(user -> user.getFractions().stream())
                .max(UsersDatabase::compareFractionsByValue)
                .orElse(null);
    }

    /**
     * Compare two fractions by their mathematical value using cross-multiplication,
     * avoiding double precision issues.
     */
    private static int compareFractionsByValue(Fraction a, Fraction b) {
        long left = (long) a.getNumerator() * (long) b.getDenominator();
        long right = (long) b.getNumerator() * (long) a.getDenominator();
        return Long.compare(left, right);
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 4
    // ---------------------------------------------------------
    /**
     * Search 4: findFirstDecimalFractionByUserName
     *
     * Returns the decimal value of the first fraction (in insertion order)
     * of the first user matching the given name.
     *
     * If user is not found or user has no fractions, returns null.
     */
    public Double findFirstDecimalFractionByUserName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }

        User user = this.usersById.values().stream()
                .filter(u -> u != null && name.equals(u.getName()))
                .findFirst()
                .orElse(null);

        if (user == null || user.getFractions().isEmpty()) {
            return null;
        }

        Fraction first = user.getFractions().get(0);
        return ((double) first.getNumerator()) / ((double) first.getDenominator());
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 8
    // ---------------------------------------------------------
    /**
     * Search 8: findUserFamilyNameBySomeImproperFraction
     *
     * Returns the family name of a user who has at least one improper fraction.
     * If no such user exists, returns null.
     */
    public String findUserFamilyNameBySomeImproperFraction() {
        return this.usersById.values().stream()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isImproper))
                .map(User::getFamilyName)
                .findFirst()
                .orElse(null);
    }
}
