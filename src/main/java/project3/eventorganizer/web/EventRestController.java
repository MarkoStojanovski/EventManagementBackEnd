package project3.eventorganizer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.service.EventService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEvents(
            @RequestParam(required = false) String category,
            @RequestParam(value = "page", defaultValue = "6") int page
            ) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Event> events = this.eventService.findByCriteria(category, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("events",events.getContent());
        response.put("totalPages",events.getTotalPages());
        response.put("currentPage",events.getNumber());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(
            @PathVariable Long id
    ) {
        Event event = this.eventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestBody Event event
    ) {
        Event newEvent = this.eventService.createEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event event
    ) {
        Event updatedEvent = this.eventService.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(
            @PathVariable Long id
    ) {
        this.eventService.softDelete(id);
        return ResponseEntity.ok().build();
    }

}
