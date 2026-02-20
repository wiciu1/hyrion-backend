package pl.hyrion.hyrionbackend.offer.command.domain;

import org.junit.jupiter.api.Test;
import pl.hyrion.hyrionbackend.offer.command.domain.exception.InvalidSalaryException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SalaryTest {

    @Test
    void shouldCreateExactSalary() {
        // given & when
        Salary salary = Salary.exact(new BigDecimal("15000"), "PLN", "MONTHLY");

        // then
        assertThat(salary.isExact()).isTrue();
        assertThat(salary.min()).isEqualTo(new BigDecimal("15000"));
        assertThat(salary.max()).isEqualTo(new BigDecimal("15000"));
    }

    @Test
    void shouldCreateRangeSalary() {
        // given & when
        Salary salary = Salary.range(new BigDecimal("10000"), new BigDecimal("15000"), "PLN", "MONTHLY");

        // then
        assertThat(salary.isExact()).isFalse();
        assertThat(salary.min()).isEqualTo(new BigDecimal("10000"));
        assertThat(salary.max()).isEqualTo(new BigDecimal("15000"));
    }

    @Test
    void shouldThrowExceptionWhenMinWageIsGreaterThanMaxWage() {
        // expect
        assertThatThrownBy(() -> Salary.range(new BigDecimal("20000"), new BigDecimal("15000"), "PLN", "MONTHLY"))
                .isInstanceOf(InvalidSalaryException.class)
                .hasMessageContaining("Minimum wage cannot be greater than maximum");
    }

    @Test
    void shouldThrowExceptionWhenWageIsNegative() {
        // expect
        assertThatThrownBy(() -> Salary.exact(new BigDecimal("-100"), "PLN", "MONTHLY"))
                .isInstanceOf(InvalidSalaryException.class)
                .hasMessageContaining("Minimum wage cannot be less than zero");
    }
}