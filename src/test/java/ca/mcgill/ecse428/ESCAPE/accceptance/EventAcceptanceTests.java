package ca.mcgill.ecse428.ESCAPE.accceptance;

import org.junit.jupiter.api.*;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.model.*;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

public class EventAcceptanceTests {

    //EID001
    @Test
    //@Given("The admin is trying to create an event: \\(p2)")
    public void adminCreatesEvent_EventDisplayedInList(){
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);
        
        // Act
        String eventName = event.getName();
        String eventDescription = event.getDescription();
        double eventTicketPrice = event.getTicketPrice();
        int eventEventId = event.getId();
        int eventCapacity = event.getCapacity();
        String eventStartTime = event.getStartTime();
        
        // Assert
        assertEquals(name, eventName);
        assertEquals(description, eventDescription);
        assertEquals(ticketPrice, eventTicketPrice);
        assertEquals(eventId, eventEventId);
        assertEquals(capacity, eventCapacity);
        assertEquals(startTime, eventStartTime);
    }    

    //EID002
    @Test
    //@Given("The admin is trying to create an event capacity for an existing event: \\(p2)")
    public void adminCreatesEventCapacity_EventDisplaysCapacity() {
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        // Act
        int updatedCapacity = 200;
        event.setCapacity(updatedCapacity);

        // Assert
        assertEquals(updatedCapacity, event.getCapacity());
    }

    //EID003
    @Test
    public void adminCreatesEventTime_EventDisplaysTime() {
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        String newTime = "7:00 PM";

        // Act
        event.setStartTime(newTime);
        String eventTime = event.getStartTime(); 

        // Assert
        assertEquals(newTime, eventTime);
    }


    //EID004
    @Test
    public void adminCreatesEventDescription_EventDisplaysDescription() {
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        // Act
        String updatedDescription = "Updated description";
        event.setDescription(updatedDescription);

        // Assert
        assertEquals(updatedDescription, event.getDescription());

    }

    //EID005
    @Test
    public void adminCreatesEventTicketPrice_EventDisplaysTicketPrice() {
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        // Act
        double updatedTicketPrice = 30.00;
        event.setTicketPrice(updatedTicketPrice);

        // Assert
        assertEquals(updatedTicketPrice, event.getTicketPrice(), 0.001);
    }


    //EID014
    @Test
    public void adminDeletesEvent_EventNoLongerDisplayedInList(){
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event eventToDelete = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        List<Event> events = new ArrayList<>();
        events.add(eventToDelete);

        // Act
        boolean eventDeleted = events.remove(eventToDelete);

        // Assert
        assertTrue(eventDeleted);
        assertFalse(events.contains(eventToDelete));
    }

    //EID015
    @Test
    public void adminDeletesEventTime_EventNoLongerHasName(){
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        // Act
        event.deleteStartTime();

        // Assert
        assertNull(event.getStartTime());
    }

    //EID013
    @Test
    public void adminDeletesEventDescription_EventNoLongerHasDescription(){
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        // Act
        event.deleteDescription();

        // Assert
        assertNull(event.getDescription());

    }

    //EID016
    @Test
    public void adminDeletesEventCapacity_EventNoLongerHasCapacity(){
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        // Act
        event.setCapacity(0);

        // Assert
        assertEquals(event.getCapacity(), 0);
    }

    //EID018
    @Test
    public void attendeeViewsEvent_EventIsDisplayedInList(){
        // Arrange
        String name = "Event";
        String description = "Description";
        double ticketPrice = 20.50;
        int eventId = 1;
        int capacity = 100;
        String startTime = "7:00";
        Event event = new Event(name, description, ticketPrice, eventId, capacity, startTime);

        Attendee attendee = new Attendee("John", "john@gmail.com", "password", null, 1);

        // Act
        List<Event> eventDetails = attendee.getEvents();

        // Assert
        String expectedDetails = "Event: " + name + "\nDescription: " + description + "\nPrice: $" + ticketPrice +
        "\nCapacity: " + capacity + "\nStart time: " + startTime;
        assertEquals(expectedDetails, eventDetails.get(0).toString());
    }

}
