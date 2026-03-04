package pl.hyrion.hyrionbackend.offer.query.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence.JobOfferEntity;
import pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence.JobOfferJpaRepository;
import pl.hyrion.hyrionbackend.offer.query.model.JobOfferView;
import pl.hyrion.hyrionbackend.offer.command.domain.exception.OfferNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobOfferQueryService {

    private final JobOfferJpaRepository repository;

    public List<JobOfferView> getAllJobOffers() {
        return repository.findAll().stream()
                .map(this::mapToView)
                .toList();
    }

    public JobOfferView getJobOfferById(UUID id) {
        return repository.findById(id)
                .map(this::mapToView)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found with id: " + id));
    }

    private JobOfferView mapToView (JobOfferEntity entity) {
        String salaryDisplayed = formatSalary(entity);

        return new JobOfferView(
                entity.getId(),
                entity.getTitle(),
                entity.getLocation(),
                salaryDisplayed,
                entity.getSkills(),
                entity.isRemote(),
                entity.getExperienceLevel(),
                entity.getCreatedAt(),
                entity.getValidTo(),
                entity.getUrl()
        );
    }

    private String formatSalary(JobOfferEntity entity) {
        if (entity.getMinSalary() == null && entity.getMaxSalary() == null) {
            return "Undisclosed salary";
        }
        if (entity.getMinSalary() != null && entity.getMinSalary().equals(entity.getMaxSalary())) {
            return entity.getMinSalary() + " " + entity.getCurrency();
        }
        return entity.getMinSalary() + " - " + entity.getMaxSalary() + " " + entity.getCurrency();
    }



}
