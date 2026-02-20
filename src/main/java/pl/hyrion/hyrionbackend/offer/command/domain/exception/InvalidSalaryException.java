package pl.hyrion.hyrionbackend.offer.command.domain.exception;

public class InvalidSalaryException extends RuntimeException {
    public InvalidSalaryException(String message) {
        super(message);
    }
}
