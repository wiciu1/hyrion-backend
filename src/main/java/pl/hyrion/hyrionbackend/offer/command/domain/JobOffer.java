package pl.hyrion.hyrionbackend.offer.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.ExperienceLevel;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.SourcePlatform;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.Status;
import pl.hyrion.hyrionbackend.offer.command.domain.exception.InvalidJobOfferException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Agregate
@Getter
public class JobOffer {

    private final JobOfferId id;
    private String title;
    private String url;
    private String location;

    private Salary salary;

    @Getter(AccessLevel.NONE)
    private Set<String> skills;
    private boolean isRemote;
    private LocalDateTime createdAt;
    private LocalDateTime validTo;

    private String originalId;
    private ExperienceLevel experienceLevel;
    private SourcePlatform sourcePlatform;
    private Status status;

    public JobOffer(String title, String url, String location, Salary salary, Set<String> skills, boolean isRemote,
                    LocalDateTime createdAt, LocalDateTime validTo, String originalId, ExperienceLevel experienceLevel,
                    SourcePlatform sourcePlatform) {

        if (title == null || title.isBlank()) {
            throw new InvalidJobOfferException("Title cannot be blank");
        }
        if (url == null || url.isBlank()) {
            throw new InvalidJobOfferException("Offer URL cannot be blank");
        }
        if (sourcePlatform == null) {
            throw new InvalidJobOfferException("Source platform cannot be null");
        }
        this.id = JobOfferId.generate();

        this.title = title;
        this.url = url;
        this.location = location;
        this.salary = salary;

        this.skills = skills != null ? new HashSet<>(skills) : new HashSet<>();
        this.isRemote = isRemote;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.validTo = validTo;
        this.originalId = originalId;
        this.experienceLevel = experienceLevel;
        this.sourcePlatform = sourcePlatform;
        this.status = Status.PUBLISHED;
    }

    // Business methods
    public void archive() {
        if (this.status == Status.ARCHIVED) {
            throw new InvalidJobOfferException("Offer is already archived");
        }
        this.status = Status.ARCHIVED;
    }

    // Custom Getters
    // Immutable list of skills
    public Set<String> getSkills() {
        return Collections.unmodifiableSet(skills);
    }
}
