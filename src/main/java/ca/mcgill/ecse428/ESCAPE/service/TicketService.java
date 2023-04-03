package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import ca.mcgill.ecse428.ESCAPE.dto.RegisterRequestDto;
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
		double price = 0;
		String name = "";
		int eventId = 0;
		// check for required fields in given requestDTO
		try {
			price = request.getPrice();
			name = request.getName();
			eventId = request.getEventId();
		} catch (RestClientException e) {
    		throw new EscapeException(HttpStatus.BAD_REQUEST, "Required attributes missing.");
		}
		// check for linked objects in the database
		Event event = eventRepository.findEventById(eventId);
    	if (event == null) {
    		throw new EscapeException(HttpStatus.NOT_FOUND, "Event not found.");
    	}
    	// create, save, and return successfully created object
    	Ticket ticket = new Ticket(price, name, event);
    	ticketRepository.save(ticket);
    	return new TicketResponseDto(ticket);
    }

    @Transactional
    public TicketResponseDto registerAttendee(RegisterRequestDto request) throws EscapeException{
        String attendeeEmail = "";
        int ticketId = 0;
        // check for required fields in given requestDTO
        try {
            attendeeEmail = request.getAttendeeEmail();
            ticketId = request.getTicketId();
        } catch (RestClientException e) {
            throw new EscapeException(HttpStatus.BAD_REQUEST, "Required attributes missing.");
        }

        Attendee attendee = attendeeRepository.findAttendeeByEmail(attendeeEmail);
        if (attendee == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
        }

        // check for linked objects in the database
        Ticket ticket = ticketRepository.findTicketByTicketId(ticketId);
        if (ticket == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Ticket not found.");
        }

//        int remainingTickets = ticket.getEvent().getCapacity() - ticket.getAttendees().toArray().length;
//        if (remainingTickets <= 0){
//            throw new EscapeException(HttpStatus.BAD_REQUEST, "No more tickets available");
//        }

        // Add ticket to attendee and attendee to ticket
        attendee.addTicket(ticket);
        ticket.addAttendee(attendee);

        // Save both modified objects
        ticketRepository.save(ticket);
        attendeeRepository.save(attendee);
        return new TicketResponseDto(ticket);
    }

    @Transactional
    public TicketResponseDto unregisterAttendee(RegisterRequestDto request) throws EscapeException{
        String attendeeEmail = "";
        int ticketId = 0;
        // check for required fields in given requestDTO
        try {
            attendeeEmail = request.getAttendeeEmail();
            ticketId = request.getTicketId();
        } catch (RestClientException e) {
            throw new EscapeException(HttpStatus.BAD_REQUEST, "Required attributes missing.");
        }

        Attendee attendee = attendeeRepository.findAttendeeByEmail(attendeeEmail);
        if (attendee == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
        }

        // check for linked objects in the database
        Ticket ticket = ticketRepository.findTicketByTicketId(ticketId);
        if (ticket == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Ticket not found.");
        }

        // Add ticket to attendee and attendee to ticket
        attendee.removeTicket(ticket);
        ticket.removeAttendee(attendee);

        // Save both modified objects
        ticketRepository.save(ticket);
        attendeeRepository.save(attendee);
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
        ticket.delete();
    }
}