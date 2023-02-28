package ca.mcgill.ecse428.ESCAPE.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // set random port
public class AttendeeIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	@Autowired
	private AttendeeRepository attendeeRepo;

	@BeforeEach
	public void clearDatabase() {
		attendeeRepo.deleteAll();
	}

	@Test
	public void testCreateAndGetAndDeleteAttendee() {
		String email = testCreateAttendee();
		testGetAttendee(email);
		//testDeleteAttendee(email);
	}

	private String testCreateAttendee() {
		// set up attendee
		String name = "aName";
		String email = "anEmail";
		AttendeeDto dto = new AttendeeDto(name, email);
		String password = "password";
		dto.setPassword(password);

		// call method: create a new attendee
		ResponseEntity<AttendeeDto> response = client.postForEntity("/attendee", dto, AttendeeDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(name, response.getBody().name, "Response has correct name");
		assertEquals(email, response.getBody().email, "Response has correct email");
		return response.getBody().email;
	}

	private void testGetAttendee(String email) {

		// call method: get the attendee by their email
		ResponseEntity<AttendeeDto> response = client.getForEntity("/attendee/" + email, AttendeeDto.class);

		// check response
		assertNotNull(response);
		System.out.println(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(email, response.getBody().email, "Response has correct email");
	}

	@Test
	public void testCreateInvalidAttendee() {
		ResponseEntity<String> response = client.postForEntity("/attendee", new AttendeeDto("  ","   "), String.class);
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Response has correct status");
	}

	@Test
	public void testGetInvalidAttendee() {
		ResponseEntity<String> response = client.getForEntity("/attendee/" + Integer.MAX_VALUE, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
		assertEquals("Attendee not found.", response.getBody(), "Response has correct error message");
	}

	// test delete attendee
	private void testDeleteAttendee(String email) {
		client.delete("/attendee/" + email);
		try {
			client.getForEntity("/attendee/" + email, String.class);
			fail("Attendee was found!");
		} catch (RestClientException | IllegalArgumentException e) {
		}
	}
}

class AttendeeDto {
	
	public String name;
	public String email;
	public String password;
	public int userId;
	public String role;

	// Need default constructor so that Jackson can instantiate the object
	public AttendeeDto() {
	}

	public AttendeeDto(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
