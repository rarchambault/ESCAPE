package ca.mcgill.ecse428.ESCAPE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ca.mcgill.ecse428.ESCAPE.dto.EventRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // create event
 	@PostMapping("/event")
 	public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto request) {
 		EventResponseDto response = eventService.createEvent(request);
 		return new ResponseEntity<EventResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @PostMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") int eventId, Model model) {

        eventService.deleteEvent(eventId);

        return "redirect:/events";
    }

    @GetMapping("/event/{id}")
	public ResponseEntity<EventResponseDto> getEventByID(@PathVariable int id) {
    	System.out.println(id);
		Event event = eventService.getEventById(id);
		return new ResponseEntity<EventResponseDto>(new EventResponseDto(event),
				HttpStatus.OK);
	}


}