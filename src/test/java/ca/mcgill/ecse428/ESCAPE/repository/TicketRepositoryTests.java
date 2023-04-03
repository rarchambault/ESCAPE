package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;

@SpringBootTest
public class TicketRepositoryTests {

    @Autowired
    private TicketRepository ticketRepository;
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
        Ticket aTicket = new Ticket();
        
        // create and link an event
        Event anEvent = new Event();
        eventRepository.save(anEvent);
        aTicket.setEvent(anEvent);

        // Save object
        aTicket = ticketRepository.save(aTicket);
        int TicketId = aTicket.getTicketId();

        // Read object from database
        aTicket = null;
        aTicket = ticketRepository.findTicketByTicketId(TicketId);

        // Assert that object has correct attributes
        assertNotNull(aTicket);
        assertEquals(TicketId, aTicket.getTicketId());
    }
    
}