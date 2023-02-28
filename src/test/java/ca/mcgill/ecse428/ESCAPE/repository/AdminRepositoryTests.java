package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Admin;


@SpringBootTest
public class AdminRepositoryTests {

    @Autowired
    private AdminRepository adminRepository;
    // TO DO add these when the repos have been made
    //private PostRepository postRepository;
    //private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;

    @AfterEach
    public void clearDatabase() {
      adminRepository.deleteAll();
      //postRepository.deleteAll();
      //ticketRepository.deleteAll();
      adminRepository.deleteAll();
    }
    
    @Test
    public void testPersistAndLoadAdmin() {
        // Create object
        Admin anAdmin = new Admin();
        anAdmin.setEmail("email@gmail.com");

        // Save object
        anAdmin = adminRepository.save(anAdmin);
        String email = anAdmin.getEmail();

        // Read object from database
        anAdmin = adminRepository.findAdminByEmail(email);

        // Assert that object has correct attributes
        assertNotNull(anAdmin);
        assertEquals(email, anAdmin.getEmail());
    }
    
    // TO DO implement tests when the relevant classes are done (repositories)
    @Test
    public void testAdminToTicketReference() {
    }

    @Test
    public void testAdminToPostReference() {
    }

    @Test
    public void testAdminToEventReference() {
    }
    
}