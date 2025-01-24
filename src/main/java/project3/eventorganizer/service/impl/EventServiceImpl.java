package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Location;
import project3.eventorganizer.models.User;
import project3.eventorganizer.models.exceptions.*;
import project3.eventorganizer.repository.EventRepository;
import project3.eventorganizer.repository.LocationRepository;
import project3.eventorganizer.repository.UserRepository;
import project3.eventorganizer.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event findById(Long id) {
        return this.eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        User user = this.userRepository.findById(event.getOrganizer().getEmail())
                .orElseThrow(UserNotFoundException::new);

        Location location = this.locationRepository.findById(event.getLocation().getId())
                .orElseThrow(LocationNotFoundException::new);

        if (user.getRole().toString().equals("ATTENDEE")){
            throw new EventCreateAttendeeException();
        }
        Event newEvent = new Event(
          event.getTitle(),
          event.getDescription(),
          event.getCategory(),
          event.getEventDateTime(),
          event.getMaxCapacity(),
          location,
          user
        );
        return this.eventRepository.save(newEvent);
    }

    @Override
    @Transactional
    public Event updateEvent(Long id, Event event) {
        Event updatedEvent = this.eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        User user = this.userRepository.findById(event.getOrganizer().getEmail())
                .orElseThrow(UserNotFoundException::new);

        Location location = this.locationRepository.findById(event.getLocation().getId())
                .orElseThrow(LocationNotFoundException::new);

        if (!(user.getRole().toString().equals("ORGANIZER") &&
                event.getOrganizer().getEmail().equals(user.getEmail())
             )
             || !user.getRole().toString().equals("ADMIN")
        ){
            throw new EventUpdateException();
        }
        updatedEvent.setTitle(event.getTitle());
        updatedEvent.setCategory(event.getCategory());
        updatedEvent.setDescription(event.getDescription());
        updatedEvent.setMaxCapacity(event.getMaxCapacity());
        updatedEvent.setLocation(location);
        updatedEvent.setOrganizer(user);
        updatedEvent.setEventDateTime(event.getEventDateTime());

        return this.eventRepository.save(updatedEvent);
    }

    @Override
    public void softDelete(Long id) {
        Event event = this.eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
        event.setDeleted(true);

        this.eventRepository.save(event);
    }
}
