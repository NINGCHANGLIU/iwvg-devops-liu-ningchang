package es.upm.miw.iwvg.devops.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class FractionTest {

    @Test
    void testConstructorAndGetters() {
        Fraction f = new Fraction(2, 5);
        assertThat(f.getNumerator()).isEqualTo(2);
        assertThat(f.getDenominator()).isEqualTo(5);
    }

    @Test
    void testDecimalPositive() {
        Fraction f = new Fraction(1, 2);
        assertThat(f.decimal()).isEqualTo(0.5, withPrecision(1e-9));
    }

    @Test
    void testDecimalNegative() {
        Fraction f = new Fraction(-1, 2);
        assertThat(f.decimal()).isEqualTo(-0.5, withPrecision(1e-9));
    }
}
