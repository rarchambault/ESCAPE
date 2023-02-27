package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.ESCAPE.dto.EventRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EventException;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;

@Service
public class EventService {

	@Autowired
    EventRepository eventRepository;

	@Transactional
    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

	@Transactional
    public Event getEventById(int id) throws EventException {
        Event event = eventRepository.findEventById(id);
        if (event == null) {
            throw new EventException(HttpStatus.NOT_FOUND, "Event not found.");
        } 
        return event;
    }

    // Add user profile when the repo is made
	@Transactional
    public EventResponseDto createEvent(EventRequestDto request) {
    	UserProfile creator = null;
    	// check if the name is blank
		String name = request.getName();
		if (name.isBlank()) {
			throw new EventException(HttpStatus.BAD_REQUEST, "Invalid event name.");
		}

    	Event event = new Event(request.getName(), request.getDescription(), request.getTicketPrice(), request.getCapacity(), request.getStartTime());

    	eventRepository.save(event);
    	return new EventResponseDto(event);
    }

	@Transactional
    public void updateEvent(int id, Event event) throws EventException {
        Event optionalEvent = eventRepository.findEventById(id);
        if (optionalEvent != null) {
            event.setEventId(id);
            eventRepository.save(event);
        } else {
            throw new EventException(HttpStatus.NOT_FOUND, "Event not found.");
        }
    }

	@Transactional
    public void deleteEvent(int id) throws EventException {
        Event event = eventRepository.findEventById(id);
        if (event == null) {
        	throw new EventException(HttpStatus.NOT_FOUND, "Event not found.");
        } 
        eventRepository.delete(event);
    }
}