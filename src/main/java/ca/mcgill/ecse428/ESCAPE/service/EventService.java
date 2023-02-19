package ca.mcgill.ecse428.ESCAPE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.ESCAPE.exception.EventException;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) throws EventException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            throw new EventException(HttpStatus.NOT_FOUND, "Event not found with id: " + id);
        }
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public void updateEvent(int id, Event event) throws EventException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            event.setEventId(id);
            eventRepository.save(event);
        } else {
            throw new EventException(HttpStatus.NOT_FOUND, "Event not found with id: " + id);
        }
    }

    public void deleteEvent(int id) throws EventException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.delete(optionalEvent.get());
        } else {
            throw new EventException(HttpStatus.NOT_FOUND, "Event not found with id: " + id);
        }
    }
}