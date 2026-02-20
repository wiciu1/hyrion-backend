package pl.hyrion.hyrionbackend.offer.command.domain.exception;

public class InvalidJobOfferException extends RuntimeException {
    public InvalidJobOfferException(String message) {
        super(message);
    }
}
