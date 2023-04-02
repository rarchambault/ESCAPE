package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.dto.RegisterRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
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

import ca.mcgill.ecse428.ESCAPE.dto.TicketRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.service.TicketService;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // create ticket
 	@PostMapping("/ticket")
 	public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketRequestDto request) {
 		TicketResponseDto response = ticketService.createTicket(request);
 		return new ResponseEntity<TicketResponseDto>(response, HttpStatus.CREATED);
 	}

	@PostMapping("/ticket/register")
	public ResponseEntity<TicketResponseDto> registerAttendee(@RequestBody RegisterRequestDto request) {
		TicketResponseDto response = ticketService.registerAttendee(request);
		return new ResponseEntity<TicketResponseDto>(response, HttpStatus.CREATED);
	}

	@PostMapping("/ticket/unregister")
	public ResponseEntity<TicketResponseDto> unregisterAttendee(@RequestBody RegisterRequestDto request) {
		TicketResponseDto response = ticketService.unregisterAttendee(request);
		return new ResponseEntity<TicketResponseDto>(response, HttpStatus.CREATED);
	}
    
    @DeleteMapping("/ticket/{id}")
    public void deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping(value = "/ticket/{id}")
	public ResponseEntity<TicketResponseDto> getTicketById(@PathVariable int id) {
		Ticket ticket = ticketService.getTicketById(id);
		return new ResponseEntity<TicketResponseDto>(new TicketResponseDto(ticket),
				HttpStatus.OK);
	}

	@GetMapping("/ticket")
	public ResponseEntity<Iterable<TicketResponseDto>> getAllTickets() {
		Iterable<Ticket> tickets = ticketService.getAllTickets();

		ArrayList<TicketResponseDto> ticketResponseDtos = new ArrayList<TicketResponseDto>();

		for (var ticket : tickets) {
			ticketResponseDtos.add(new TicketResponseDto(ticket));
		}

		return new ResponseEntity<Iterable<TicketResponseDto>>(ticketResponseDtos, HttpStatus.OK);
	}
}