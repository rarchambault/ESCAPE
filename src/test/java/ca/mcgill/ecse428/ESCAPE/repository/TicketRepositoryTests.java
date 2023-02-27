package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse428.ESCAPE.model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Ticket;

import java.time.LocalDateTime;

@SpringBootTest
public class TicketRepositoryTests {

    @Autowired
    private TicketRepository ticketRepository;
    // TO DO add these when the repos have been made
    //private PhotoGalleryRepository photoGalleryRepository;
    //private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;

    @AfterEach
    public void clearDatabase() {
      ticketRepository.deleteAll();
      //photoGalleryRepository.deleteAll();
      //ticketRepository.deleteAll();
      eventRepository.deleteAll();
    }
    
    @Test
    public void testPersistAndLoadTicket() {
        // Create object
        Ticket anTicket = new Ticket();
        Event event = new Event("Power hour", "Description", 20, 100, LocalDateTime.of(2023, 03, 10, 7, 0));
        anTicket.setEvent(event);

        // Save object
        anTicket = ticketRepository.save(anTicket);
        int TicketId = 1;

        // Read object from database
        anTicket = null;
        anTicket = ticketRepository.findTicketByTicketId(TicketId);

        // Assert that object has correct attributes
        assertNotNull(anTicket);
        assertEquals(TicketId, anTicket.getTicketId());
    }
    
    // TO DO implement tests when the relevant classes are done (repositories)
    @Test
    public void testTicketToAttendeeReference() {
    }
    
}