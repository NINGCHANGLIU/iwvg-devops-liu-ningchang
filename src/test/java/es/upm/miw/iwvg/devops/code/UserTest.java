package es.upm.miw.iwvg.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User("1", "Alice", "Smith");
        assertThat(user.getId()).isEqualTo("1");
        assertThat(user.getName()).isEqualTo("Alice");
        assertThat(user.getFamilyName()).isEqualTo("Smith");
    }

    @Test
    void testFullName() {
        User user = new User("1", "Alice", "Smith");
        assertThat(user.fullName()).isEqualTo("Alice Smith");
    }

    @Test
    void testInitials() {
        User user = new User("2", "Bob", "Brown");
        assertThat(user.initials()).isEqualTo("B.");
    }

    @Test
    void testAddFraction() {
        User user = new User("3", "Carol", "Lee");
        Fraction f = new Fraction(3, 4);
        user.addFraction(f);
        assertThat(user.getFractions())
                .hasSize(1)
                .first()
                .isSameAs(f);
    }

}
