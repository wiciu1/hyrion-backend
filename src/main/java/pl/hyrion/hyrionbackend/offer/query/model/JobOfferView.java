package pl.hyrion.hyrionbackend.offer.query.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


// Dto for viewing given Job Offer for user
public record JobOfferView (
    UUID id,
    String title,
    String location,
    String salaryDisplayed,         // finally formatted salary
    Set<String> skills,
    boolean isRemote,
    String experienceLevel,
    LocalDateTime createdAt,
    LocalDateTime validTo,
    String sourceUrl
) {}
