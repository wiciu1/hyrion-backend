package pl.hyrion.hyrionbackend.offer.query.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence.JobOfferEntity;
import pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence.JobOfferJpaRepository;
import pl.hyrion.hyrionbackend.offer.query.model.JobOfferView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOfferQueryService {

    private final JobOfferJpaRepository repository;

    public List<JobOfferView> getAllJobOffers() {
        return repository.findAll().stream()
                .map(this::mapToView)
                .toList();
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
