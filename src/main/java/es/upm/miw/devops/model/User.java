package es.upm.miw.devops.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {

    private final int id;
    private final String name;
    private final String familyName;
    private final List<Fraction> fractions;

    public User(int id, String name, String familyName) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (familyName == null || familyName.isBlank()) {
            throw new IllegalArgumentException("Family name must not be blank");
        }
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.fractions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<Fraction> getFractions() {
        return Collections.unmodifiableList(fractions);
    }

    public void addFraction(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("Fraction must not be null");
        }
        this.fractions.add(fraction);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', familyName='" + familyName + "', fractions=" + fractions + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
