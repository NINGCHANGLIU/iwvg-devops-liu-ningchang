package es.upm.miw.devops.model;

import java.lang.reflect.Method;
import java.util.*;
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
                .flatMap(this::safeFractionsStream)
                .max(Comparator.comparingDouble(f -> (double) f.getNumerator() / f.getDenominator()))
                .orElse(null);
    }

    /**
     * Try to obtain a Stream<Fraction> from a User instance with minimal coupling.
     * Supported patterns (if present in User):
     * - getFractions(): Collection/Fraction[]/Stream
     * - fractions(): Collection/Fraction[]/Stream
     * - streamFractions(): Stream
     */
    @SuppressWarnings("unchecked")
    private Stream<Fraction> safeFractionsStream(User user) {
        if (user == null) {
            return Stream.empty();
        }

        // Try methods in a preferred order
        Stream<Fraction> stream = invokeFractionsMethod(user, "streamFractions");
        if (stream != null) return stream;

        stream = invokeFractionsMethod(user, "getFractions");
        if (stream != null) return stream;

        stream = invokeFractionsMethod(user, "fractions");
        if (stream != null) return stream;

        return Stream.empty();
    }

    @SuppressWarnings("unchecked")
    private Stream<Fraction> invokeFractionsMethod(User user, String methodName) {
        try {
            Method m = user.getClass().getMethod(methodName);
            Object result = m.invoke(user);
            if (result == null) {
                return Stream.empty();
            }
            if (result instanceof Stream) {
                return (Stream<Fraction>) result;
            }
            if (result instanceof Collection) {
                return ((Collection<Fraction>) result).stream();
            }
            if (result.getClass().isArray()) {
                Object[] array = (Object[]) result;
                return Arrays.stream(array).map(o -> (Fraction) o);
            }
            return Stream.empty();
        } catch (ReflectiveOperationException ignored) {
            return null; // means "method not found / not invokable"
        }
    }
}
