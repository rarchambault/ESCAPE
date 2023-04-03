package ca.mcgill.ecse428.ESCAPE.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.mcgill.ecse428.ESCAPE.dto.RegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse428.ESCAPE.dto.TicketRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;
import ca.mcgill.ecse428.ESCAPE.repository.TicketRepository;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {

	// Replace the repository with a "mock" that exposes the same interface
	@Mock
	TicketRepository ticketRepo;
	@Mock
	AttendeeRepository attendeeRepo;
	@Mock
	EventRepository eventRepo;

	// Get a service that uses the mock repository
	@InjectMocks
	TicketService ticketService;
	@InjectMocks
	AttendeeService attendeeService;
	@InjectMocks
	EventService eventService;

	@Test
	public void testGetTicketById() {
		// Tell the mocked repository how to behave
		final Ticket party = new Ticket();
		int id = party.getTicketId();
		when(ticketRepo.findTicketByTicketId(id)).thenAnswer((InvocationOnMock invocation) -> party);

		// Test that the service behaves properly
		Ticket ticket = ticketService.getTicketById(id);

		assertNotNull(ticket);
		assertEquals(id, ticket.getTicketId());
	}

	@Test
	public void testGetTicketByInvalidId() {
		final int invalidId = 99;

		// Mock: if asking for a ticket with invalid ID, return null
		when(ticketRepo.findTicketByTicketId(invalidId)).thenAnswer((InvocationOnMock invocation) -> null);

		// call method, and obtain resulting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> ticketService.getTicketById(invalidId));

		// check results
		assertEquals("Ticket not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

	@Test
	public void testCreateTicket() {
		// Mock: just return the ticket with no modification
		when(ticketRepo.save(any(Ticket.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create an ticket request
		String name = "Party";
		int price = 17;
		// create mocks for the required associated classes
		Event event = new Event();
		int eventId = 71;
		event.setEventId(71);
		when(eventRepo.findEventById(eventId)).thenAnswer((InvocationOnMock invocation) -> event);
		
		TicketRequestDto ticketRequest = new TicketRequestDto(name, price, eventId);

		// call method
		TicketResponseDto returnedTicket = ticketService.createTicket(ticketRequest);

		// check results
		assertNotNull(returnedTicket);
		assertEquals(price, returnedTicket.getPrice());
		// Check that the service actually saved the ticket
		// verify(ticketRepo, times(1)).save();
	}

	@Test
	public void testRegisterAttendee() {
		// Mock: just return the ticket with no modification
		when(ticketRepo.save(any(Ticket.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create a registration request
		// create mocks for the required associated classes
		String attendeeEmail = "attendee@email.com";
		Attendee attendee = new Attendee();
		attendee.setEmail(attendeeEmail);
		when(attendeeRepo.findAttendeeByEmail(attendeeEmail)).thenAnswer((InvocationOnMock invocation) -> attendee);

		int ticketId = 1;
		Ticket ticket = new Ticket();
		ticket.setTicketId(ticketId);
		ticket.setEvent(new Event("name", "desc", 100, LocalDateTime.now(), "location"));
		when(ticketRepo.findTicketByTicketId(ticketId)).thenAnswer((InvocationOnMock invocation) -> ticket);

		RegisterRequestDto registerRequest = new RegisterRequestDto(attendeeEmail, ticketId);

		// call method
		TicketResponseDto returnedTicket = ticketService.registerAttendee(registerRequest);

		// check results
		assertNotNull(returnedTicket);
		assertNotNull(returnedTicket.getAttendeeEmails());
		assertEquals(1, returnedTicket.getAttendeeEmails().size());
	}

	@Test
	public void testUnregisterAttendee() {
		// Mock: just return the ticket with no modification
		when(ticketRepo.save(any(Ticket.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create a registration request
		// create mocks for the required associated classes
		String attendeeEmail = "attendee@email.com";
		Attendee attendee = new Attendee();
		attendee.setEmail(attendeeEmail);
		when(attendeeRepo.findAttendeeByEmail(attendeeEmail)).thenAnswer((InvocationOnMock invocation) -> attendee);

		int ticketId = 1;
		Ticket ticket = new Ticket();
		ticket.setTicketId(ticketId);
		ticket.setEvent(new Event("Power Hour", "Description", 100, LocalDateTime.now(), "Location"));
		when(ticketRepo.findTicketByTicketId(ticketId)).thenAnswer((InvocationOnMock invocation) -> ticket);

		RegisterRequestDto registerRequest = new RegisterRequestDto(attendeeEmail, ticketId);

		// call method
		TicketResponseDto ticketWithAttendee = ticketService.registerAttendee(registerRequest);

		// check results
		assertNotNull(ticketWithAttendee);
		assertNotNull(ticketWithAttendee.getAttendeeEmails());
		assertEquals(1, ticketWithAttendee.getAttendeeEmails().size());

		// call method
		TicketResponseDto ticketNoAttendee = ticketService.unregisterAttendee(registerRequest);

		// check results
		assertNotNull(ticketNoAttendee);
		assertEquals(0, ticketNoAttendee.getAttendeeEmails().size());
	}
	
	@Test
	public void testGetAllTickets() {
		
	}

	// test delete ticket
	@Test
	public void testDeleteTicket() {
		Ticket anTicket = new Ticket();
		int id = anTicket.getTicketId();

		// Mock: if looking for post using this posts ID return the post as if it were in the database
		when(ticketRepo.findTicketByTicketId(id)).thenAnswer(x -> anTicket);

		// call method
		ticketService.deleteTicket(id);

		// check results
		verify(ticketRepo, times(1)).delete(anTicket);
	}

	// test invalid delete ticket - not found
	@Test
	public void testInvalidDeleteTicket() {
		// Mock: if searching using an invalid ticket id return null
		when(ticketRepo.findTicketByTicketId(any(int.class))).thenAnswer(x -> null);

		// call method, expecting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> ticketService.deleteTicket(Integer.MAX_VALUE));

		// check results
		assertEquals(ex.getMessage(), "Ticket not found.");
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

}
