package ca.mcgill.ecse428.ESCAPE.accceptance;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.model.*;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

public class EventAcceptanceTests {

    //EID001
    @Test
    public void adminCreatesEvent_EventDisplayedInList(){
        //arrange
        Event event = new Event();
        Admin admin = new Admin();

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.createEvent(event);
        
        // Assert
        List<Event> events = Event.getAllEvents();
        assertTrue(events.contains(event));
    }    

    //EID002
    @Test
    public void adminCreatesEventCapacity_EventDisplaysCapacity() {
        // Arrange
        Event event = new Event();
        Admin admin = new Admin();
        String name = "New Event";
        int capacity = 100;
        event.setName(name);
        event.setCapacity(capacity);

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.createNewEvent(event);

        // Assert
        Event createdEvent = Event.getEvent(event.getId());
        assertEquals(name, createdEvent.getName());
        assertEquals(capacity, createdEvent.getCapacity());
    }

    //EID003
    @Test
    public void adminCreatesEventTime_EventDisplaysTime() {
        // Arrange
        Event event = new Event();
        Admin admin = new Admin();
        Time time = new Time("12:00pm");
        event.setName("Test Event");
        event.create();

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.selectEvent(event);
        admin.addEventTime(time);
        admin.saveEventChanges();

        // Assert
        event = Event.getEvent(event.getId());
        assertTrue(event.getTimes().contains(time));
    }


    //EID004
    @Test
    public void adminCreatesEventDescription_EventDisplaysDescription() {
        // Arrange
        Event event = new Event();
        Admin admin = new Admin();
        String description = "This is a test event description.";
        event.setDescription(description);
        
        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.createEvent(event);
        
        // Assert
        Event createdEvent = Event.getEvent(event.getId());
        assertNotNull(createdEvent);
        assertEquals(description, createdEvent.getDescription());
    }

    //EID005
    @Test
    public void adminCreatesEventTicketPrice_EventDisplaysTicketPrice() {
        // Arrange
        Event event = new Event();
        Admin admin = new Admin();
        event.setName("Concert");
        event.setTicketPrice(50.0);
        event.create();
        List<Event> events = Event.getAllEvents();
        assertTrue(events.contains(event));
        double eventTicketPrice = event.getTicketPrice();
        assertEquals(50.0, eventTicketPrice);

        // Act
        admin.login();
        admin.navigateToEventManagementPage();
        admin.selectEvent(event);
        admin.editEventTicketPrice(75.0);
        admin.saveEventChanges();

        // Assert
        event = Event.getEvent(event.getId());
        double editedTicketPrice = event.getTicketPrice();
        assertEquals(75.0, editedTicketPrice);
    }


    //EID014
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

    //EID015
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

    //EID013
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

    //EID016
    @Test
    public void adminDeletesEventCapacity_EventNoLongerHasCapacity(){
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
        admin.deleteEventCapacity();
        
        // Assert
        events = Event.getAllEvents();
        assertFalse(events.get(0).getCapacity().isPresent());
        
    }

    //EID018
    @Test
    public void attendeeViewsEvent_EventIsDisplayedInList(){
        // Arrange
        Event event = new Event();
        event.create();
        List<Event> events = Event.getAllEvents();
        assertTrue(events.contains(event));

        Attendee attendee = new Attendee();
        attendee.login();

        // Act
        List<Event> attendeeEvents = attendee.viewEvents();

        // Assert
        assertTrue(attendeeEvents.contains(event));
    }
}
