package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.EventSponsor;
import project3.eventorganizer.models.Sponsor;
import project3.eventorganizer.models.exceptions.EventNotFoundException;
import project3.eventorganizer.models.exceptions.EventSponsorNotFoundException;
import project3.eventorganizer.models.exceptions.SponsorNotFoundException;
import project3.eventorganizer.repository.EventRepository;
import project3.eventorganizer.repository.EventSponsorRepository;
import project3.eventorganizer.repository.SponsorRepository;
import project3.eventorganizer.service.EventSponsorService;
import project3.eventorganizer.service.SponsorService;

import java.util.List;

@Service
public class EventSponsorServiceImpl implements EventSponsorService {

    private final EventSponsorRepository eventSponsorRepository;
    private final EventRepository eventRepository;
    private final SponsorRepository sponsorRepository;

    public EventSponsorServiceImpl(EventSponsorRepository eventSponsorRepository, EventRepository eventRepository, SponsorRepository sponsorRepository) {
        this.eventSponsorRepository = eventSponsorRepository;
        this.eventRepository = eventRepository;
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public EventSponsor findById(Long id) {
        return this.eventSponsorRepository.findById(id)
                .orElseThrow(EventSponsorNotFoundException::new);
    }

    @Override
    public List<EventSponsor> findAll() {
        return this.eventSponsorRepository.findAll();
    }

    @Override
    @Transactional
    public EventSponsor createEventSponsor(Event event, Sponsor sponsor) {
        Event withEvent = this.eventRepository.findById(event.getId())
                .orElseThrow(EventNotFoundException::new);

        Sponsor withSponsor = this.sponsorRepository.findById(sponsor.getId())
                .orElseThrow(SponsorNotFoundException::new);

        EventSponsor newEventSponsor = new EventSponsor(
                event,
                sponsor
        );

        return this.eventSponsorRepository.save(newEventSponsor);
    }

    @Override
    @Transactional
    public EventSponsor updateEventSponsor(Long id, Event event, Sponsor sponsor) {
        EventSponsor updatedEventSponsor = this.eventSponsorRepository.findById(id)
                .orElseThrow(EventSponsorNotFoundException::new);
        Event withEvent = this.eventRepository.findById(event.getId())
                .orElseThrow(EventNotFoundException::new);

        Sponsor withSponsor = this.sponsorRepository.findById(sponsor.getId())
                .orElseThrow(SponsorNotFoundException::new);

        updatedEventSponsor.setEvent(withEvent);
        updatedEventSponsor.setSponsor(withSponsor);

        return this.eventSponsorRepository.save(updatedEventSponsor);
    }

    @Override
    public void softDelete(Long id) {
        EventSponsor eventSponsor = this.eventSponsorRepository.findById(id)
                .orElseThrow(EventSponsorNotFoundException::new);

        eventSponsor.setDeleted(true);
        this.eventSponsorRepository.save(eventSponsor);
    }
}
