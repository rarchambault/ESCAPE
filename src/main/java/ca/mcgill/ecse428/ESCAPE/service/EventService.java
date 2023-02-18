package ca.mcgill.ecse428.ESCAPE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(long id) throws EventNotFoundException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public void updateEvent(long id, Event event) throws EventNotFoundException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            event.setId(id);
            eventRepository.save(event);
        } else {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
    }

    public void deleteEvent(long id) throws EventNotFoundException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.delete(optionalEvent.get());
        } else {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
    }
}