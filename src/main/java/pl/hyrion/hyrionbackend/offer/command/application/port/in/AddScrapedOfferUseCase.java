package pl.hyrion.hyrionbackend.offer.command.application.port.in;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface AddScrapedOfferUseCase {

    void addOffer(AddOfferCommand command);

    record AddOfferCommand(
            String title,
            String url,
            String location,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            String currency,
            String paymentSchedule,
            Set<String> skills,
            boolean isRemote,
            LocalDateTime createdAt,
            LocalDateTime validTo,
            String originalId,
            String experienceLevel,
            String sourcePlatform
    ) {}

}
