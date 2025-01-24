package project3.eventorganizer.service;

import project3.eventorganizer.models.Event;

import java.util.List;

public interface EventService {

    Event findById(Long id);

    List<Event> findAll();

    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);


    void softDelete(Long id);

}
