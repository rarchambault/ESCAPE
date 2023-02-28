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
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.repository.AdminRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // set random port
public class AdminIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	@Autowired
	private AdminRepository adminRepo;

	@BeforeEach
	public void clearDatabase() {
		adminRepo.deleteAll();
	}

	@Test
	public void testCreateAndGetAndDeleteAdmin() {
		String email = testCreateAdmin();
		testGetAdmin(email);
		//testDeleteAdmin(email);
	}

	private String testCreateAdmin() {
		// set up admin
		String name = "aName";
		String email = "anEmail";
		AdminDto dto = new AdminDto(name, email);
		String password = "password";
		dto.setPassword(password);

		// call method: create a new admin
		ResponseEntity<AdminDto> response = client.postForEntity("/admin", dto, AdminDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(name, response.getBody().name, "Response has correct name");
		assertEquals(email, response.getBody().email, "Response has correct email");
		return response.getBody().email;
	}

	private void testGetAdmin(String email) {

		// call method: get the admin by their email
		ResponseEntity<AdminDto> response = client.getForEntity("/admin/" + email, AdminDto.class);

		// check response
		assertNotNull(response);
		System.out.println(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(email, response.getBody().email, "Response has correct email");
	}

	@Test
	public void testCreateInvalidAdmin() {
		ResponseEntity<String> response = client.postForEntity("/admin", new AdminDto("  ","   "), String.class);
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Response has correct status");
	}

	@Test
	public void testGetInvalidAdmin() {
		ResponseEntity<String> response = client.getForEntity("/admin/" + Integer.MAX_VALUE, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
		assertEquals("Admin not found.", response.getBody(), "Response has correct error message");
	}

	// test delete admin
	private void testDeleteAdmin(String email) {
		client.delete("/admin/" + email);
		try {
			client.getForEntity("/admin/" + email, String.class);
			fail("Admin was found!");
		} catch (RestClientException | IllegalArgumentException e) {
		}
	}
}

class AdminDto {
	
	public String name;
	public String email;
	public String password;
	public int userId;
	public String role;

	// Need default constructor so that Jackson can instantiate the object
	public AdminDto() {
	}

	public AdminDto(String name, String email) {
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
