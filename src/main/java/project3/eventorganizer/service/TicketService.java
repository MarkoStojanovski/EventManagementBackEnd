package project3.eventorganizer.service;

import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.models.Ticket;
import project3.eventorganizer.models.enumerations.TicketType;

import java.util.List;

public interface TicketService {

    Ticket findById(Long id);

    List<Ticket> findAll();

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Long id, Ticket ticket);

    void softDelete(Long id);

}
