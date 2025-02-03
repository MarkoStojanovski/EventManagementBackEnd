package project3.eventorganizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT T FROM Ticket AS T WHERE T.isDeleted = false")
    Page<Ticket> findAllTickets(Pageable pageable);

    @Query("SELECT T FROM Ticket AS T WHERE T.isDeleted = false AND T.event.id = :eventId")
    Page<Ticket> findTicketByEvent(@Param("eventId") Long eventId, Pageable pageable);

}
