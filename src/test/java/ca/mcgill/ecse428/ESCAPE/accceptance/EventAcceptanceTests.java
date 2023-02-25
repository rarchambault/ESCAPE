package ca.mcgill.ecse428.ESCAPE.accceptance;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.model.*;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

public class EventAcceptanceTests {
    
    @Test
    public void adminDeletesEvent_EventNoLongerDisplayedInList(){
        //arrange
        Event Event = new Event();
        Admin admin = new Admin();
        event.create();
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

    @Test
    public void adminDeletesEventTime_EventNoLongerHasName(){
        // Arrange
        Event event = new Event();
        Admin admin = new Admin();
        String name;
        event.setName(name);
        event.create();
        List<Event> events = Event.getAllEvents();
        assertTrue(events.contains(event));
        String eventName = event.getName();
        assertTrue(eventName.equals(name));

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.selectEvent(event);
        admin.editEventName(null);
        admin.saveEventChanges();

        // Assert
        event = Event.getEvent(event.getId());
        String editedEventName = event.getName();
        assertNull(editedEventName);

    }

    @Test
    public void adminDeletesEventDescription_EventNoLongerHasDescription(){
        //arrange
        Event event = new Event();
        Admin admin = new Admin();
        event.create();
        List<Event> events = Event.getAllEvents();
        assertTrue(events.contains(event));

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.selectEvent(event);
        admin.deleteEventDescription();

        // Assert
        events = Event.getAllEvents();
        assertFalse(events.get(0).getDescription().isPresent());

    }


}
