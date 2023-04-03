package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.service.AttendeeService;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class AttendeeController{

    @Autowired
    private AttendeeService attendeeService;

    // create attendee
 	@PostMapping("/attendee")
 	public ResponseEntity<UserProfileResponseDto> createAttendeeProfile(@RequestBody UserProfileRequestDto request) {
 		UserProfileResponseDto response = attendeeService.createAttendee(request);
 		return new ResponseEntity<UserProfileResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @DeleteMapping("/attendee/{email}")
    public void deleteAttendee(@PathVariable String email) {
        attendeeService.deleteAttendee(email);
    }

    @GetMapping(value = "/attendee/{email}")
	public ResponseEntity<UserProfileResponseDto> getAttendeeById(@PathVariable String email) {
		Attendee attendee = attendeeService.getAttendeeByEmail(email);
		return new ResponseEntity<UserProfileResponseDto>(new UserProfileResponseDto(attendee, "Attendee"),
				HttpStatus.OK);
	}

	@GetMapping("/attendee")
	public ResponseEntity<Iterable<UserProfileResponseDto>> getAllAttendees() {
		Iterable<Attendee> attendees = attendeeService.getAllAttendees();

		ArrayList<UserProfileResponseDto> attendeeResponses = new ArrayList<UserProfileResponseDto>();

		for (var attendee : attendees) {
			attendeeResponses.add(new UserProfileResponseDto(attendee, "attendee"));
		}

		return new ResponseEntity<Iterable<UserProfileResponseDto>>(attendeeResponses, HttpStatus.OK);
	}

	@GetMapping("/attendee/{email}/tickets")
	public ResponseEntity<Iterable<TicketResponseDto>> getAllTicketsForAttendee(@PathVariable String email) {
		Iterable<Ticket> tickets = attendeeService.getAllTicketsForAttendee(email);

		ArrayList<TicketResponseDto> ticketResponses = new ArrayList<TicketResponseDto>();

		for (var ticket : tickets) {
			ticketResponses.add(new TicketResponseDto(ticket));
		}

		return new ResponseEntity<Iterable<TicketResponseDto>>(ticketResponses, HttpStatus.OK);
	}
}