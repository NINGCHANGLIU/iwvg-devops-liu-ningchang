package es.upm.miw.devops.code;

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
}
