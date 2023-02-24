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

import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // set random port
public class EventIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	@Autowired
	private EventRepository eventRepo;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		eventRepo.deleteAll();
	}

	@Test
	public void testCreateAndGetAndDeleteEvent() {
		int id = testCreateEvent();
		testGetEvent(id);
		testDeleteEvent(id);
	}

	private int testCreateEvent() {
		Event event = new Event();
        event.setName("Test Event");
        event.setDescription("Test Description");
        event.setName("Test name");
        event.setTicketPrice(12);
        event.setEventId(21);
        return event.getId();
	}

	private void testGetEvent(int id) {

		// call method: get the event by their id
		ResponseEntity<EventDto> response = client.getForEntity("/event/" + id, EventDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertTrue(response.getBody().id == id, "Response has correct ID");
	}

	@Test
	public void testCreateInvalidEvent() {
		ResponseEntity<String> response = client.postForEntity("/event", new EventDto("   "), String.class);
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
	}

	@Test
	public void testGetInvalidEvent() {
		ResponseEntity<String> response = client.getForEntity("/event/" + Integer.MAX_VALUE, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
		assertEquals("Event not found.", response.getBody(), "Response has correct error message");
	}

	// test delete event
	private void testDeleteEvent(int id) {
		client.delete("/event/" + id);
		try {
			client.getForEntity("/event/" + id, EventResponseDto.class);
			fail("Event was found!");
		} catch (RestClientException | IllegalArgumentException e) {
		}
	}
}

class EventDto {
	public int id;
	public String name;

	// Need default constructor so that Jackson can instantiate the object
	public EventDto() {
	}

	public EventDto(String name) {
		this.name = name;
	}
}
