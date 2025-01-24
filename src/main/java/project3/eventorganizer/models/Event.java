package project3.eventorganizer.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project3.eventorganizer.models.enumerations.Category;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_title")
    @NotBlank(message = "Event must have a title !")
    private String title;

    @NotBlank(message = "The Event must have information about the whole event !")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Future(message = "Event must be in the Future !")
    private LocalDateTime eventDateTime;

    @Min(0)
    @Column(name = "event_capacity")
    private int maxCapacity;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Event(String title, String description, Category category, LocalDateTime eventDateTime, int maxCapacity, Location location, User organizer) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.eventDateTime = eventDateTime;
        this.maxCapacity = maxCapacity;
        this.location = location;
        this.organizer = organizer;
    }
}
