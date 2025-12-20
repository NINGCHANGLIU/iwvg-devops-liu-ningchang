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
    public Fraction findHighestFraction() {
        return this.usersById.values().stream()
                .flatMap(user -> user.getFractions().stream())
                .max(UsersDatabase::compareFractionsByValue)
                .orElse(null);
    }

    private static int compareFractionsByValue(Fraction a, Fraction b) {
        long left = (long) a.getNumerator() * (long) b.getDenominator();
        long right = (long) b.getNumerator() * (long) a.getDenominator();
        return Long.compare(left, right);
    }

    // ---------------------------------------------------------
    // Bug fix (was Sprint 3 - Search 4)
    // ---------------------------------------------------------
    /**
     * BUGFIX: Search 4
     *
     * Now returns the decimal value of the FIRST **PROPER** fraction
     * of the first user matching the given name.
     *
     * If user is not found or has no proper fractions, returns null.
     */
    public Double findFirstDecimalFractionByUserName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }

        return this.usersById.values().stream()
                .filter(user -> name.equals(user.getName()))
                .flatMap(user -> user.getFractions().stream())
                .filter(Fraction::isProper)
                .findFirst()
                .map(f -> (double) f.getNumerator() / f.getDenominator())
                .orElse(null);
    }

    // ---------------------------------------------------------
    // Sprint 3 - Search 8
    // ---------------------------------------------------------
    public String findUserFamilyNameBySomeImproperFraction() {
        return this.usersById.values().stream()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isImproper))
                .map(User::getFamilyName)
                .findFirst()
                .orElse(null);
    }
}
