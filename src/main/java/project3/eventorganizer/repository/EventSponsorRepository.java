package project3.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.EventSponsor;

@Repository
public interface EventSponsorRepository extends JpaRepository<EventSponsor, Long> {
}
