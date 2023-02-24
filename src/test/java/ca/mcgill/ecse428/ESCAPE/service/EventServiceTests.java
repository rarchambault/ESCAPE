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

import ca.mcgill.ecse428.ESCAPE.exception.EventException;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {

	// Replace the repository with a "mock" that exposes the same interface
	@Mock
	EventRepository eventRepo;

	// Get a service that uses the mock repository
	@InjectMocks
	EventService eventService;

	@Test
	public void testGetEventById() {
	}

	@Test
	public void testGetEventByInvalidId() {
	}

	@Test
	public void testCreateEvent() {
	}

	// test delete event
	@Test
	public void testDeleteEvent() {
	}

	// test invalid delete event - not found
	@Test
	public void testInvalidDeleteEvent() {
		// Mock: if searching using an invalid event id return null
		when(eventRepo.findEventById(any(int.class))).thenAnswer(x -> null);

		// call method, expecting exception
		EventException ex = assertThrows(EventException.class,
				() -> eventService.deleteEvent(Integer.MAX_VALUE));

		// check results
		assertEquals(ex.getMessage(), "Event not found.");
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

}
