package ca.mcgill.ecse428.ESCAPE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@CrossOrigin(origins = "*")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    // create event
 	@PostMapping("/event")
 	public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto request) {
 		EventResponseDto response = eventService.createEvent(request);
 		return new ResponseEntity<EventResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable int id) {

        eventService.deleteEvent(id);
        return "redirect:/event";
    }

    @GetMapping(value = "/event/{id}")
	public ResponseEntity<EventResponseDto> getEventById(@PathVariable int id) {
		Event event = eventService.getEventById(id);
		return new ResponseEntity<EventResponseDto>(new EventResponseDto(event),
				HttpStatus.OK);
	}


}