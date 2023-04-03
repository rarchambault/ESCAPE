package ca.mcgill.ecse428.ESCAPE.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse428.ESCAPE.dto.RegisterRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.TicketRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;
import ca.mcgill.ecse428.ESCAPE.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // set random port
public class TicketIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private AttendeeRepository attendeeRepo;
	@Autowired
	private EventRepository eventRepo;

	@BeforeEach
	public void clearDatabase() {
		attendeeRepo.deleteAll();
		eventRepo.deleteAll();
		ticketRepo.deleteAll();
	}

	@Test
	public void testCreateAndGetAndDeleteTicket() {
		int id = testCreateTicket();
		testGetTicket(id);
		//testRegisterAttendee(id);
		testUnregisterAttendee(id);
		//testDeleteTicket(id);
	}

	private int testCreateTicket() {
		
		// setup - first create and save required associated class objects
		Attendee person = new Attendee();
		String email = "obi@kenobi.com";
		person.setEmail(email);
		attendeeRepo.save(person);
		Event event = new Event();
		eventRepo.save(event);
		int eventId = event.getId();
		
		// set up ticket
		int price = 33;
		String name = "a ticket name";
		TicketDto dto = new TicketDto(price, name, eventId);

		// call method: create a new ticket
		ResponseEntity<TicketResponseDto> response = client.postForEntity("/ticket", dto, TicketResponseDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(name, response.getBody().getName(), "Response has correct name");

		return response.getBody().getTicketId();
	}

	private void testRegisterAttendee(int ticketId) {

		// setup - first create and save required associated class objects
		Attendee person = new Attendee();
		String email = "obi@kenobi.com";
		person.setEmail(email);
		attendeeRepo.save(person);

		RegisterRequestDto requestDto = new RegisterRequestDto(email, ticketId);

		// call method: create a new ticket
		ResponseEntity<TicketResponseDto> response = client.postForEntity("/ticket/register", requestDto, TicketResponseDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(email, response.getBody().getAttendeeEmail(0), "Response has correct attendee registered");
	}

	private void testUnregisterAttendee(int ticketId) {

		// setup - first create and save required associated class objects
		Attendee person = new Attendee();
		String email = "obi@kenobi.com";
		person.setEmail(email);
		attendeeRepo.save(person);

		RegisterRequestDto requestDto = new RegisterRequestDto(email, ticketId);

		// call method: create a new ticket
		ResponseEntity<TicketResponseDto> response = client.postForEntity("/ticket/unregister", requestDto, TicketResponseDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(0, response.getBody().getAttendeeEmails().size(), "Response has no attendees registered");
	}

	private void testGetTicket(int id) {

		// call method: get the ticket by their id
		ResponseEntity<TicketDto> response = client.getForEntity("/ticket/" + id, TicketDto.class);

		// check response
		assertNotNull(response);
		System.out.println(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertTrue(response.getBody().ticketId == id, "Response has correct ID");
	}

	@Test
	public void testGetInvalidTicket() {
		ResponseEntity<String> response = client.getForEntity("/ticket/" + Integer.MAX_VALUE, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
		assertEquals("Ticket not found.", response.getBody(), "Response has correct error message");
	}

	// test delete ticket
	private void testDeleteTicket(int id) {
		client.delete("/ticket/" + id);
		try {
			client.getForEntity("/ticket/" + id, String.class);
			fail("Ticket was found!");
		} catch (RestClientException | IllegalArgumentException e) {
		}
	}
}

class TicketDto {
	
	public int price;
	public String name;
	public int eventId;
	public int ticketId;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public TicketDto(int price, String name, int eventId) {
		super();
		this.price = price;
		this.name = name;
		this.eventId = eventId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	// Need default constructor so that Jackson can instantiate the object
	public TicketDto() {
	}
}
