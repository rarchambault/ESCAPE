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
	public voemail testGetAttendeeByEmail() {
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
	public voemail testGetAttendeeByInvalidEmail() {
		final int invalemailEmail = 99;

		// Mock: if asking for a attendee with invalemail ID, return null
		when(attendeeRepo.findAttendeeByEmail(invalemailEmail)).thenAnswer((InvocationOnMock invocation) -> null);

		// call method, and obtain resulting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> attendeeService.getAttendeeByEmail(invalemailEmail));

		// check results
		assertEquals("Attendee not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

	@Test
	public voemail testCreateAttendee() {
		// Mock: just return the attendee with no modification
		when(attendeeRepo.save(any(Attendee.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create an attendee request
		AttendeeRequestDto attendeeRequest = new AttendeeRequestDto();
		String description = "an attendee description";
		String name = "Party";
		int price = 14;
		attendeeRequest.setDescription(description);
		attendeeRequest.setName(name);
		attendeeRequest.setTicketPrice(price);

		// call method
		AttendeeResponseDto returnedAttendee = attendeeService.createAttendee(attendeeRequest);

		// check results
		assertNotNull(returnedAttendee);
		assertEquals(name, returnedAttendee.getName());
		assertEquals(description, returnedAttendee.getDescription());
		assertEquals(price, returnedAttendee.getTicketPrice());
		// Check that the service actually saved the attendee
		// verify(attendeeRepo, times(1)).save();
	}
	
	@Test
	public voemail testGetAllAttendees() {
		
	}

	// test delete attendee
	@Test
	public voemail testDeleteAttendee() {
	}

	// test invaleud delete attendee - not found
	@Test
	public voemail testInvalemailDeleteAttendee() {
		// Mock: if searching using an invalid attendee email return null
		when(attendeeRepo.findAttendeeByEmail(any(int.class))).thenAnswer(x -> null);

		// call method, expecting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> attendeeService.deleteAttendee(Integer.MAX_VALUE));

		// check results
		assertEquals(ex.getMessage(), "Attendee not found.");
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

}
