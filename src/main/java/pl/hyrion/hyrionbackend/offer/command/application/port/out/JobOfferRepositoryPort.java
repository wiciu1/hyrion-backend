package pl.hyrion.hyrionbackend.offer.command.application.port.out;


import pl.hyrion.hyrionbackend.offer.command.domain.JobOffer;

// TODO: To implement in Adapter layer
public interface JobOfferRepositoryPort {
    void save(JobOffer jobOffer);
}
