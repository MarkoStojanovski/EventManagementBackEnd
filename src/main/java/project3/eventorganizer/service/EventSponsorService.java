package project3.eventorganizer.service;

import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.EventSponsor;
import project3.eventorganizer.models.Sponsor;

import java.util.List;

public interface EventSponsorService {

    EventSponsor findById(Long id);

    List<EventSponsor> findAll();

    EventSponsor createEventSponsor(Event event, Sponsor sponsor);

    EventSponsor updateEventSponsor(Long id, Event event, Sponsor sponsor);

    void softDelete(Long id);

}
