package ca.mcgill.ecse428.ESCAPE.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;
    // TO DO add these when the repos have been made
    //private PhotoGalleryRepository photoGalleryRepository;
    //private TicketRepository ticketRepository;
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
    }
    
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