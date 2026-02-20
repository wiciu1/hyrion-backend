package pl.hyrion.hyrionbackend.offer.command.domain;

import org.junit.jupiter.api.Test;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.ExperienceLevel;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.SourcePlatform;
import pl.hyrion.hyrionbackend.offer.command.domain.enums.Status;
import pl.hyrion.hyrionbackend.offer.command.domain.exception.InvalidJobOfferException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JobOfferTest {

    @Test
    void shouldCreateValidJobOffer() {
        // given
        Salary salary = Salary.exact(new BigDecimal("15000"), "PLN", "MONTHLY");

        // when
        JobOffer offer = new JobOffer(
                "Java Developer",
                "https://hyrion/offers/123",
                "Warsaw",
                salary,
                Set.of("Java", "Spring"),
                true,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                "hyrion-123",
                ExperienceLevel.MID,
                SourcePlatform.JUSTJOINIT
        );

        // then
        assertThat(offer.getId()).isNotNull();
        assertThat(offer.getStatus()).isEqualTo(Status.PUBLISHED);
        assertThat(offer.getSkills()).containsExactlyInAnyOrder("Java", "Spring");
    }

    @Test
    void shouldThrowExceptionWhenTitleIsBlank() {
        // given
        Salary salary = Salary.exact(new BigDecimal("15000"), "PLN", "MONTHLY");

        // expect
        assertThatThrownBy(() -> new JobOffer(
                "",
                "https://hyrion/offers/123",
                "Warsaw",
                salary,
                Set.of("Java"),
                true,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                "hyrion-123",
                ExperienceLevel.MID,
                SourcePlatform.JUSTJOINIT
        ))
                .isInstanceOf(InvalidJobOfferException.class)
                .hasMessageContaining("Title cannot be blank");
    }

    @Test
    void shouldSuccessfullyArchiveOffer() {
        // given
        JobOffer offer = createDummyOffer();
        assertThat(offer.getStatus()).isEqualTo(Status.PUBLISHED);

        // when
        offer.archive();

        // then
        assertThat(offer.getStatus()).isEqualTo(Status.ARCHIVED);
    }

    @Test
    void shouldThrowExceptionWhenArchivingAlreadyArchivedOffer() {
        // given
        JobOffer offer = createDummyOffer();
        offer.archive();

        assertThatThrownBy(offer::archive)
                .isInstanceOf(InvalidJobOfferException.class)
                .hasMessageContaining("Offer is already archived");
    }

    private JobOffer createDummyOffer() {
        return new JobOffer(
                "Test Title", "http://test.com", "Location",
                Salary.exact(new BigDecimal("100"), "PLN", "MONTHLY"),
                Set.of("Test"), true, LocalDateTime.now(), LocalDateTime.now(),
                "123", ExperienceLevel.JUNIOR, SourcePlatform.NOFLUFFJOBS
        );
    }
}