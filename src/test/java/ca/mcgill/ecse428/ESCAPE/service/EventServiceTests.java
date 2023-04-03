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

import ca.mcgill.ecse428.ESCAPE.dto.EventRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EventException;
import ca.mcgill.ecse428.ESCAPE.model.Event;
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
		// Tell the mocked repository how to behave
		final Event party = new Event();
		int id = party.getId();
		when(eventRepo.findEventById(id)).thenAnswer((InvocationOnMock invocation) -> party);

		// Test that the service behaves properly
		Event event = eventService.getEventById(id);

		assertNotNull(event);
		assertEquals(id, event.getId());
	}

	@Test
	public void testGetEventByInvalidId() {
		final int invalidId = 99;

		// Mock: if asking for a event with invalid ID, return null
		when(eventRepo.findEventById(invalidId)).thenAnswer((InvocationOnMock invocation) -> null);

		// call method, and obtain resulting exception
		EventException ex = assertThrows(EventException.class,
				() -> eventService.getEventById(invalidId));

		// check results
		assertEquals("Event not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

	@Test
	public void testCreateEvent() {
		// Mock: just return the event with no modification
		when(eventRepo.save(any(Event.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create an event request
		EventRequestDto eventRequest = new EventRequestDto();
		String description = "an event description";
		String name = "Party";
		int price = 14;
		eventRequest.setDescription(description);
		eventRequest.setName(name);

		// call method
		EventResponseDto returnedEvent = eventService.createEvent(eventRequest);

		// check results
		assertNotNull(returnedEvent);
		assertEquals(name, returnedEvent.getName());
		assertEquals(description, returnedEvent.getDescription());
		// Check that the service actually saved the event
		// verify(eventRepo, times(1)).save();
	}
	
	@Test
	public void testGetAllEvents() {
		
	}

	// test delete event
	@Test
	public void testDeleteEvent() {
		Event anEvent = new Event();
		int id = anEvent.getId();

		// Mock: if looking for post using this posts ID return the post as if it were in the database
		when(eventRepo.findEventById(id)).thenAnswer(x -> anEvent);

		// call method
		eventService.deleteEvent(id);

		// check results
		verify(eventRepo, times(1)).delete(anEvent);
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
