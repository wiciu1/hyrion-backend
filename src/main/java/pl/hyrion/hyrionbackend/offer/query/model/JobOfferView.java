package pl.hyrion.hyrionbackend.offer.query.model;

import pl.hyrion.hyrionbackend.offer.command.domain.JobOfferId;

import java.time.LocalDateTime;
import java.util.Set;


// Dto for viewing given Job Offer for user
public record JobOfferView (
    JobOfferId id,
    String title,
    String location,
    String salaryDisplayed,         // finally formatted salary
    Set<String> skills,
    boolean isRemote,
    String experienceLevel,
    LocalDateTime createdAt,
    String sourceUrl
) {}
