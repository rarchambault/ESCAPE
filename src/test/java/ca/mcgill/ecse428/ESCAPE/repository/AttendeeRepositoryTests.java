package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Attendee;


@SpringBootTest
public class AttendeeRepositoryTests {

    @Autowired
    private AttendeeRepository attendeeRepository;
    // TO DO add these when the repos have been made
    //private PostRepository postRepository;
    //private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;

    @AfterEach
    public void clearDatabase() {
      attendeeRepository.deleteAll();
      //postRepository.deleteAll();
      //ticketRepository.deleteAll();
      attendeeRepository.deleteAll();
    }
    
    @Test
    public void testPersistAndLoadAttendee() {
        // Create object
        Attendee anAttendee = new Attendee();
        anAttendee.setEmail("email@gmail.com");

        // Save object
        anAttendee = attendeeRepository.save(anAttendee);
        String email = anAttendee.getEmail();

        // Read object from database
        anAttendee = attendeeRepository.findAttendeeByEmail(email);

        // Assert that object has correct attributes
        assertNotNull(anAttendee);
        assertEquals(email, anAttendee.getEmail());
    }
    
    // TO DO implement tests when the relevant classes are done (repositories)
    @Test
    public void testAttendeeToTicketReference() {
    }

    @Test
    public void testAttendeeToPostReference() {
    }

    @Test
    public void testAttendeeToEventReference() {
    }
    
}