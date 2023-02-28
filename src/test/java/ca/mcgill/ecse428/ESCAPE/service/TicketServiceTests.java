package ca.mcgill.ecse428.ESCAPE.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		Attendee person = new Attendee();
		String email = "dawg@oo.com";
		person.setEmail(email);
		when(attendeeRepo.findAttendeeByEmail(email)).thenAnswer((InvocationOnMock invocation) -> person);
		Event event = new Event();
		int eventId = 71;
		event.setEventId(71);
		when(eventRepo.findEventById(eventId)).thenAnswer((InvocationOnMock invocation) -> event);
		
		TicketRequestDto ticketRequest = new TicketRequestDto(name, price, eventId, email);

		// call method
		TicketResponseDto returnedTicket = ticketService.createTicket(ticketRequest);

		// check results
		assertNotNull(returnedTicket);
		assertEquals(price, returnedTicket.getPrice());
		// Check that the service actually saved the ticket
		// verify(ticketRepo, times(1)).save();
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
