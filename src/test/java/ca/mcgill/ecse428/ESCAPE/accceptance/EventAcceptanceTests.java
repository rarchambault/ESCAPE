package ca.mcgill.ecse428.ESCAPE.accceptance;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.model.*;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

public class EventAcceptanceTests {
    
    @Test
    public void adminDeletesEvent_EventNoLongerDisplayedInList(){
        //arrange
        Event Event = new Event();
        Admin admin = new Admin();
        Event.create();
        List<Event> Events = Event.getAllEvents();
        assertTrue(Events.contains(Event));

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.selectEvent(Event);
        admin.deleteEvent();

        // Assert
        Events = Event.getAllEvents();
        assertFalse(Events.contains(Event));
    }
}
