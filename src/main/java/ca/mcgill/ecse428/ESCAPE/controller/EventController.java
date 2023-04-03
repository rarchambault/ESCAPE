package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.ESCAPE.dto.EventRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.service.EventService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class EventController {

	private final StorageService storageService;

	@Autowired
	public EventController(StorageService storageService) {
		this.storageService = storageService;
	}

    @Autowired
    private EventService eventService;

    // create event
 	@PostMapping("/event")
 	public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto request) {
 		EventResponseDto response = eventService.createEvent(request);
 		return new ResponseEntity<EventResponseDto>(response, HttpStatus.CREATED);
 	}

	@PostMapping("/eventPicture/{id}")
	public ResponseEntity<String> uploadPicture(@PathVariable int id, @RequestParam("file") MultipartFile file) {
		storageService.store(file);
		if(eventService.set_picture_path(id, file.getOriginalFilename())){
			return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
					.body(String.format("Successfully uploaded %s!", file.getOriginalFilename()));
		}
		return ResponseEntity.badRequest().body("Event picture upload failed");
	}

	@GetMapping("/eventPicture/{id}")
	public ResponseEntity<Resource> serveFile(@PathVariable int id) {
		String filename = eventService.get_picture_path(id);
		if(filename == "" || filename == null) {
			return ResponseEntity.noContent().build();
		}
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
    
    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable int id) {

        eventService.deleteEvent(id);
    }

    @GetMapping(value = "/event/{id}")
	public ResponseEntity<EventResponseDto> getEventById(@PathVariable int id) {
		Event event = eventService.getEventById(id);
		return new ResponseEntity<EventResponseDto>(new EventResponseDto(event),
				HttpStatus.OK);
	}

	@GetMapping("/event")
	public ResponseEntity<Iterable<EventResponseDto>> getAllEvents() {
		Iterable<Event> events = eventService.getAllEvents();

		ArrayList<EventResponseDto> eventResponseDtos = new ArrayList<EventResponseDto>();

		for (var event : events) {
			eventResponseDtos.add(new EventResponseDto(event));
		}

		return new ResponseEntity<Iterable<EventResponseDto>>(eventResponseDtos, HttpStatus.OK);
	}
  
  @PostMapping("/event/{id}/update")
  	public ResponseEntity<EventResponseDto> updateEvent(@PathVariable int id, @RequestBody EventRequestDto eventRequestDto) {
		// Fetch the existing event from the database
		Event existingEvent = eventService.getEventById(id);

		existingEvent.setName(eventRequestDto.getName());
		existingEvent.setDescription(eventRequestDto.getDescription());
		existingEvent.setLocation(eventRequestDto.getLocation());
		existingEvent.setCapacity(eventRequestDto.getCapacity());
		// Convert the updated event to a response DTO and return it with a success status code
		EventResponseDto eventResponseDto = new EventResponseDto(existingEvent);
		return ResponseEntity.ok(eventResponseDto);
	}
}