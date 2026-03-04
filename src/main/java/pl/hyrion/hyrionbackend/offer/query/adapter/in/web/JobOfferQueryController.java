package pl.hyrion.hyrionbackend.offer.query.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hyrion.hyrionbackend.offer.query.application.service.JobOfferQueryService;
import pl.hyrion.hyrionbackend.offer.query.model.JobOfferView;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class JobOfferQueryController {

    private final JobOfferQueryService queryService;

    @GetMapping
    public ResponseEntity<List<JobOfferView>> getOffers() {
        List<JobOfferView> offers = queryService.getAllJobOffers();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferView> getOfferById(@PathVariable UUID id) {
        JobOfferView offer = queryService.getJobOfferById(id);
        return ResponseEntity.ok(offer);
    }
}
