package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Event;


@SpringBootTest
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;
    // TO DO add these when the repos have been made
    //private PhotoGalleryRepository photoGalleryRepository;
    //private TicketRepository ticketRepository;
    @Autowired
    private AttendeeRepository attendeeRepository;

    @AfterEach
    public void clearDatabase() {
      eventRepository.deleteAll();
      //photoGalleryRepository.deleteAll();
      //ticketRepository.deleteAll();
      attendeeRepository.deleteAll();
    }
    
    @Test
    public void testPersistAndLoadEvent() {
        // Create object
        Event anEvent = new Event();

        // Save object
        anEvent = eventRepository.save(anEvent);
        int eventId = anEvent.getId();

        // Read object from database
        anEvent = null;
        anEvent = eventRepository.findEventById(eventId);

        // Assert that object has correct attributes
        assertNotNull(anEvent);
        assertEquals(eventId, anEvent.getId());
    }
    
    // TO DO implement tests when the relevant classes are done (repositories)
    @Test
    public void testEventToTicketReference() {
    }

    @Test
    public void testEventToPhotoGalleryReference() {
    }

    @Test
    public void testEventToAttendeeReference() {
    }
    
}