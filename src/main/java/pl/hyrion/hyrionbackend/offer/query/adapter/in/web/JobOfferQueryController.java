package pl.hyrion.hyrionbackend.offer.query.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hyrion.hyrionbackend.offer.query.application.service.JobOfferQueryService;
import pl.hyrion.hyrionbackend.offer.query.model.JobOfferView;

import java.util.List;

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
}
