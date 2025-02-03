package project3.eventorganizer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project3.eventorganizer.models.enumerations.TicketType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(name = "ticket_prices")
    @Min(0)
    private double price;

    @Column(name = "quantity")
    @Min(0)
    private int quantity;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;

    public Ticket(TicketType ticketType, double price,int quantity, Event event) {
        this.ticketType = ticketType;
        this.price = price;
        this.quantity = quantity;
        this.event = event;
    }
}
