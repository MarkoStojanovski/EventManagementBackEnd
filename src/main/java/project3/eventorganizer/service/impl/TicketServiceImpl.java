package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.models.Ticket;
import project3.eventorganizer.models.enumerations.TicketType;
import project3.eventorganizer.models.exceptions.EventNotFoundException;
import project3.eventorganizer.models.exceptions.PaymentNotFoundException;
import project3.eventorganizer.models.exceptions.TicketNotFoundException;
import project3.eventorganizer.repository.EventRepository;
import project3.eventorganizer.repository.PaymentRepository;
import project3.eventorganizer.repository.TicketRepository;
import project3.eventorganizer.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final PaymentRepository paymentRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository, PaymentRepository paymentRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Ticket findById(Long id) {
        return this.ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);
    }

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }
//    TicketType ticketType, double price, boolean isDeleted, Event event, Payment payment

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        Event event = this.eventRepository.findById(ticket.getEvent().getId())
                .orElseThrow(EventNotFoundException::new);
        Payment payment = this.paymentRepository.findById(ticket.getPayment().getId())
                .orElseThrow(PaymentNotFoundException::new);

        Ticket newTicket = new Ticket(
                ticket.getTicketType(),
                ticket.getPrice(),
                event,
                payment
        );
        return this.ticketRepository.save(newTicket);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket updateTicket = this.ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        Event event = this.eventRepository.findById(ticket.getEvent().getId())
                .orElseThrow(EventNotFoundException::new);

        Payment payment = this.paymentRepository.findById(ticket.getPayment().getId())
                .orElseThrow(PaymentNotFoundException::new);

        updateTicket.setTicketType(ticket.getTicketType());
        updateTicket.setPrice(ticket.getPrice());
        updateTicket.setEvent(event);
        updateTicket.setPayment(payment);

        return this.ticketRepository.save(updateTicket);
    }

    @Override
    public void softDelete(Long id) {
        Ticket ticket = this.ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        ticket.setDeleted(true);

        this.ticketRepository.save(ticket);
    }
}
