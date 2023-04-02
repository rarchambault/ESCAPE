package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
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

    	Event event = new Event(request.getName(), request.getDescription(), request.getCapacity(), request.getStartTime(), request.getLocation());

    	eventRepository.save(event);
    	return new EventResponseDto(event);
    }

    public boolean set_picture_path(int id, String profile_picture_path) {
        Event event = eventRepository.findEventById(id);
        event.set_picture_path(profile_picture_path);
        eventRepository.save(event);
        return true;
    }

    public String get_picture_path(int id) {
        Event event = null;
        try{
            event = eventRepository.findEventById(id);
        }
        catch(Exception e){
            System.out.println(e);
        }
        if (event != null) {
            return event.get_picture_path();
        } else {
            return null;
        }
    }

	@Transactional
    public void updateEvent(int id) throws EventException {
        Event optionalEvent = eventRepository.findEventById(id);
        if (optionalEvent != null) {
            optionalEvent.setEventId(id);
            eventRepository.save(optionalEvent);
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