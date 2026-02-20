package pl.hyrion.hyrionbackend.offer.command.domain;

import pl.hyrion.hyrionbackend.offer.command.domain.exception.InvalidSalaryException;

import java.math.BigDecimal;

// Value Object
public record Salary(BigDecimal min, BigDecimal max, String currency, String paymentSchedule) {

    public Salary {
        if (min != null && min.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidSalaryException("Minimum wage cannot be less than zero");
        }
        if (max != null && max.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidSalaryException("Maximum wage cannot be less than zero");
        }
        if (min != null && max != null && min.compareTo(max) > 0) {
            throw new InvalidSalaryException("Minimum wage cannot be greater than maximum");
        }
        if (currency == null || currency.isBlank()) {
            throw new InvalidSalaryException("Currency cannot be blank");
        }
        if (paymentSchedule == null || paymentSchedule.isBlank()) {
            throw new InvalidSalaryException("Payment schedule cannot be blank");
        }
    }

    // For case when salary is given in range, for example:
    // 1200 PLN - 1500 PLN / day
    public static Salary range(BigDecimal min, BigDecimal max, String currency, String paymentSchedule) {
        return new Salary(min, max, currency, paymentSchedule);
    }

    // For case when salary is exact, for example:
    // 120 PLN / hour
    public static Salary exact(BigDecimal amount, String currency, String paymentSchedule) {
        return new Salary(amount, amount, currency, paymentSchedule);
    }

    // Checks if min equals max
    // For exact value case.
    public boolean isExact() {
        if (min == null || max == null) {
            return false;
        }
        return min.compareTo(max) == 0;
    }
}
