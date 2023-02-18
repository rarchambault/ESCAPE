package ca.mcgill.ecse428.ESCAPE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // create event
 	@PostMapping("/event")
 	public String createEvent(@RequestBody EventRequestDto request) {
 		AdminResponseDto response = adminService.createAdmin(request.getPersonEmail());
 		return new ResponseEntity<AdminResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @PostMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId, Model model) {
        Event event = eventService.findEventById(eventId);

        if (event == null) {
            model.addAttribute("errorMessage", "Event not found.");
            return "error";
        }

        eventService.deleteEvent(event);

        return "redirect:/events";
    }

    @GetMapping
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "view-events";
    }


}