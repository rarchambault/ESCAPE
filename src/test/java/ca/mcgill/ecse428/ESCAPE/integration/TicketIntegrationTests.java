package ca.mcgill.ecse428.ESCAPE.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

import ca.mcgill.ecse428.ESCAPE.dto.*;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.repository.TicketRepository;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Event;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // set random port
public class TicketIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	@Autowired
	private TicketRepository ticketRepo;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		ticketRepo.deleteAll();
	}

	@Test
	public void testCreateAndGetAndDeleteTicket() {
		int id = testCreateTicket();
		testGetTicket(id);
		testDeleteTicket(id);
	}

	private int testCreateTicket() {
		Ticket ticket = new Ticket();
		Attendee attendee = new Attendee();
		Event event = new Event();
        ticket.setTicketId(1);
        ticket.setPrice(1.0);
        ticket.setName("Test name");
		ticket.addAttendee(attendee);
		ticket.setEvent(event);
        return ticket.getTicketId();
	}

	private void testGetTicket(int id) {

		// call method: get the ticket by their id
		ResponseEntity<TicketRequestDto> response = client.getForEntity("/ticket/" + id, TicketRequestDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertTrue(response.getBody().getTicketId() == id, "Response has correct ID");
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
			client.getForEntity("/ticket/" + id, TicketResponseDto.class);
			fail("Ticket was found!");
		} catch (RestClientException | IllegalArgumentException e) {
		}
	}
}
