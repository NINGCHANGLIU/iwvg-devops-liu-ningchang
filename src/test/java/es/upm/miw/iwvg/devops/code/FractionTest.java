package es.upm.miw.iwvg.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FractionTest {

    @Test
    void testFractionConstructorAndGetters() {
        Fraction fraction = new Fraction(2, 5);
        assertThat(fraction.getNumerator()).isEqualTo(2);
        assertThat(fraction.getDenominator()).isEqualTo(5);
    }

    @Test
    void testDecimal() {
        Fraction fraction = new Fraction(1, 2);
        assertThat(fraction.decimal()).isEqualTo(0.5);
    }
}
