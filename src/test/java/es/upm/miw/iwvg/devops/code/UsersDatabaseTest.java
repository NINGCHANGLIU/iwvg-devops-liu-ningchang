package es.upm.miw.iwvg.devops.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class UsersDatabaseTest {

    @Test
    void testFindAllReturnsUsers() {
        List<User> users = new UsersDatabase()
                .findAll()
                .collect(Collectors.toList());

        // 至少应该有几个用户
        assertThat(users).hasSizeGreaterThanOrEqualTo(6);

        // 检查第一个用户的信息
        User first = users.get(0);
        assertThat(first.getId()).isEqualTo("1");
        assertThat(first.getName()).isEqualTo("Oscar");
        assertThat(first.getFamilyName()).isEqualTo("Fernandez");
    }

    @Test
    void testFindAllUsersHaveFractions() {
        List<User> users = new UsersDatabase()
                .findAll()
                .collect(Collectors.toList());

        // 检查至少有一个用户带 Fraction
        assertThat(users)
                .anySatisfy(user -> assertThat(user.getFractions()).isNotEmpty());
    }
}
