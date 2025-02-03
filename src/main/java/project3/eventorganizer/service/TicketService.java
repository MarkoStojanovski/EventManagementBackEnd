package project3.eventorganizer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.models.Ticket;

public interface TicketService {

    Ticket findById(Long id);
    Page<Ticket> findAllTickets(Pageable pageable);
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Long id, Ticket ticket);
    void softDelete(Long id);
    Page<Ticket> findByEvent(Event event, Pageable pageable);
    Ticket purchaseTicket(Long ticketId, Payment payment);

}
