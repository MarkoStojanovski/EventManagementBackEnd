package project3.eventorganizer.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.EventSponsor;
import project3.eventorganizer.service.EventSponsorService;

import java.util.List;

@RestController
@RequestMapping("/api/eventsponsors")
public class EventSponsorRestController {

    private final EventSponsorService eventSponsorService;

    public EventSponsorRestController(EventSponsorService eventSponsorService) {
        this.eventSponsorService = eventSponsorService;
    }

    @GetMapping
    public ResponseEntity<List<EventSponsor>> getAllEventSponsors() {
        List<EventSponsor> eventSponsors = this.eventSponsorService.findAll();
        return new ResponseEntity<>(eventSponsors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventSponsor> getEventSponsor(
            @PathVariable Long id
    ) {
        EventSponsor eventSponsor = this.eventSponsorService.findById(id);
        return new ResponseEntity<>(eventSponsor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventSponsor> createEventSponsor(
            @RequestBody EventSponsor eventSponsor
    ) {
        EventSponsor newEventSponsor = this.eventSponsorService.createEventSponsor(eventSponsor.getEvent(), eventSponsor.getSponsor());
        return new ResponseEntity<>(newEventSponsor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventSponsor> updateEventSponsor(
            @PathVariable Long id,
            @RequestBody EventSponsor eventSponsor
    ) {
        EventSponsor updatedEventSponsor = this.eventSponsorService.updateEventSponsor(
                id,
                eventSponsor.getEvent(),
                eventSponsor.getSponsor()
        );
        return new ResponseEntity<>(updatedEventSponsor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventSponsor> deleteEventSponsor(
            @PathVariable Long id
    ) {
        this.eventSponsorService.softDelete(id);
        return ResponseEntity.ok().build();
    }

}
