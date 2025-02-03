package project3.eventorganizer.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.models.Ticket;
import project3.eventorganizer.models.exceptions.EventNotFoundException;
import project3.eventorganizer.models.exceptions.TicketNotFoundException;
import project3.eventorganizer.models.exceptions.TicketSoldOutException;
import project3.eventorganizer.repository.EventRepository;
import project3.eventorganizer.repository.PaymentRepository;
import project3.eventorganizer.repository.TicketRepository;
import project3.eventorganizer.service.TicketService;


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
                .filter(ticket -> !ticket.isDeleted())
                .orElseThrow(TicketNotFoundException::new);
    }

    @Override
    public Page<Ticket> findAllTickets(Pageable pageable) {
        return this.ticketRepository.findAllTickets(pageable);
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        Event event = this.eventRepository.findById(ticket.getEvent().getId())
                .orElseThrow(EventNotFoundException::new);

        Ticket newTicket = new Ticket(
                ticket.getTicketType(),
                ticket.getPrice(),
                ticket.getQuantity(),
                event
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

        updateTicket.setTicketType(ticket.getTicketType());
        updateTicket.setPrice(ticket.getPrice());
        updateTicket.setEvent(event);
        updateTicket.setQuantity(ticket.getQuantity());

        return this.ticketRepository.save(updateTicket);
    }

    @Override
    public void softDelete(Long id) {
        Ticket ticket = this.ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        ticket.setDeleted(true);

        this.ticketRepository.save(ticket);
    }

    @Override
    public Page<Ticket> findByEvent(Event event, Pageable pageable) {
        return this.ticketRepository.findTicketByEvent(event.getId(), pageable);
    }

    @Override
    @Transactional
    public Ticket purchaseTicket(Long ticketId, Payment payment) {
        Ticket ticket = this.ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFoundException::new);

        if (ticket.getQuantity() <= 0) {
            throw new TicketSoldOutException();
        }

        Payment savedPayment = this.paymentRepository.save(payment);
        ticket.setPayment(savedPayment);
        ticket.setQuantity(ticket.getQuantity() - 1);

        return this.ticketRepository.save(ticket);
    }
}
