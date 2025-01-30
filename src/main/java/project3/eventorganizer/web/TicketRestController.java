package project3.eventorganizer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Ticket;
import project3.eventorganizer.service.TicketService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllTickets(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Ticket> tickets = this.ticketService.findAllTickets(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("tickets", tickets.getContent());
        response.put("totalPages",tickets.getTotalPages());
        response.put("currentPage",tickets.getNumber());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(
            @PathVariable Long id
    ) {
        Ticket ticket = this.ticketService.findById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @RequestBody Ticket ticket
    ) {
        Ticket newTicket = this.ticketService.createTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable Long id,
            @RequestBody Ticket ticket
    ) {
        Ticket updateTicket = this.ticketService.updateTicket(id, ticket);
        return new ResponseEntity<>(updateTicket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(
            @PathVariable Long id
    ) {
        this.ticketService.softDelete(id);
        return ResponseEntity.ok().build();
    }

}
