package project3.eventorganizer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project3.eventorganizer.models.Event;

import java.util.List;

public interface EventService {

    Event findById(Long id);

    Page<Event> findByCriteria(String category, Pageable pageable);

    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);


    void softDelete(Long id);

}
