package project3.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
