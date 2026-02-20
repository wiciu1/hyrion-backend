package pl.hyrion.hyrionbackend.offer.command.domain;
import java.util.UUID;

// Value Object
public record JobOfferId(UUID value) {
    public static JobOfferId generate() { return new JobOfferId(UUID.randomUUID()); }
}
