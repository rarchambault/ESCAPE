package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import ca.mcgill.ecse428.ESCAPE.dto.TicketRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;
import ca.mcgill.ecse428.ESCAPE.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
    TicketRepository ticketRepository;
	@Autowired
    AttendeeRepository attendeeRepository;
	@Autowired
    EventRepository eventRepository;
	
	
	@Transactional
    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

	@Transactional
    public Ticket getTicketById(int id) throws EscapeException {
        Ticket ticket = ticketRepository.findTicketByTicketId(id);
        if (ticket == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Ticket not found.");
        } 
        return ticket;
    }

	@Transactional
    public TicketResponseDto createTicket(TicketRequestDto request) throws EscapeException{
		int price = 0;
		String name = "";
		String email = "";
		int eventId = 0;
		// check for required fields in given requestDTO
		try {
			price = request.getPrice();
			name = request.getName();
			email = request.getAttendeeEmail();
			eventId = request.getEventId();
		} catch (RestClientException e) {
    		throw new EscapeException(HttpStatus.BAD_REQUEST, "Required attributes missing.");
		}
		// check for linked objects in the database
		Attendee attendee = attendeeRepository.findAttendeeByEmail(email);
		Event event = eventRepository.findEventById(eventId);
    	if (event == null) {
    		throw new EscapeException(HttpStatus.NOT_FOUND, "Event not found.");
    	}
    	if (attendee == null) {
    		throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
    	}
    	// create, save, and return successfully created object
    	Ticket ticket = new Ticket(price, name, attendee, event);
    	ticketRepository.save(ticket);
    	return new TicketResponseDto(ticket);
    }

	@Transactional
    public void updateTicket(int id, Ticket ticket) throws EscapeException {
        Ticket optionalTicket = ticketRepository.findTicketByTicketId(id);
        if (optionalTicket != null) {
            ticket.setTicketId(id);
            ticketRepository.save(ticket);
        } else {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Ticket not found.");
        }
    }

	@Transactional
    public void deleteTicket(int id) throws EscapeException {
        Ticket ticket = ticketRepository.findTicketByTicketId(id);
        if (ticket == null) {
        	throw new EscapeException(HttpStatus.NOT_FOUND, "Ticket not found.");
        } 
        ticketRepository.delete(ticket);
    }
}