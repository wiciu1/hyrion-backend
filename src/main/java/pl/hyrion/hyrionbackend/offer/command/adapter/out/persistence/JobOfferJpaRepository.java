package pl.hyrion.hyrionbackend.offer.command.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, UUID> {
}
