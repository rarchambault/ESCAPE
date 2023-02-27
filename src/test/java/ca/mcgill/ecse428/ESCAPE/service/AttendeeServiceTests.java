package ca.mcgill.ecse428.ESCAPE.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;

@ExtendWith(MockitoExtension.class)
public class AttendeeServiceTests {

	// Replace the repository with a "mock" that exposes the same interface
	@Mock
	AttendeeRepository attendeeRepo;

	// Get a service that uses the mock repository
	@InjectMocks
	AttendeeService attendeeService;

	@Test
	public void testGetAttendeeByEmail() {
		// Tell the mocked repository how to behave
		final Attendee party = new Attendee();
		String email = party.getEmail();
		when(attendeeRepo.findAttendeeByEmail(email)).thenAnswer((InvocationOnMock invocation) -> party);

		// Test that the service behaves properly
		Attendee attendee = attendeeService.getAttendeeByEmail(email);

		assertNotNull(attendee);
		assertEquals(email, attendee.getEmail());
	}

	@Test
	public void testGetAttendeeByUnrecognizedEmail() {
		final String UnrecognizedEmail = "anUnrecognizedEmail";

		// Mock: if asking for a attendee with invalid ID, return null
		when(attendeeRepo.findAttendeeByEmail(UnrecognizedEmail)).thenAnswer((InvocationOnMock invocation) -> null);

		// call method, and obtain resulting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> attendeeService.getAttendeeByEmail(UnrecognizedEmail));

		// check results
		assertEquals("Attendee not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

	@Test
	public void testCreateAttendee() {
		// Mock: just return the attendee with no modification
		when(attendeeRepo.save(any(Attendee.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create an attendee request
		String name = "aName";
		String email = "anEmail";
		String password = "aPassword";
		UserProfileRequestDto attendeeRequest = new UserProfileRequestDto(name, email, password);
		attendeeRequest.setName(name);

		// call method
		UserProfileResponseDto returnedAttendee = attendeeService.createAttendee(attendeeRequest);

		// check results
		assertNotNull(returnedAttendee);
		assertEquals(name, returnedAttendee.getName());
		// Check that the service actually saved the attendee
		// verify(attendeeRepo, times(1)).save();
	}
	
	@Test
	public void testGetAllAttendees() {
		
	}

	// test delete attendee
	@Test
	public void testDeleteAttendee() {
	}

	// test invaleud delete attendee - not found
	@Test
	public void testInvalidDeleteAttendee() {
		// Mock: if searching using an invalid attendee email return null
		when(attendeeRepo.findAttendeeByEmail(any(String.class))).thenAnswer(x -> null);

		// call method, expecting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> attendeeService.deleteAttendee("anUnrecognizedEmail"));

		// check results
		assertEquals(ex.getMessage(), "Attendee not found.");
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

}
