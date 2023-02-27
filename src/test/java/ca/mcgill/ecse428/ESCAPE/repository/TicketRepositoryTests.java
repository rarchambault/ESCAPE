package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Ticket;

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

        // Save object
        anTicket = ticketRepository.save(anTicket);
        int TicketId = anTicket.getId();

        // Read object from database
        anTicket = null;
        anTicket = ticketRepository.findTicketById(TicketId);

        // Assert that object has correct attributes
        assertNotNull(anTicket);
        assertEquals(TicketId, anTicket.getId());
    }
    
    // TO DO implement tests when the relevant classes are done (repositories)
    @Test
    public void testTicketToAttendeeReference() {
    }
    
}