package project3.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
