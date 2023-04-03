package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// line 52 "model.ump"
// line 122 "model.ump"
@Entity
public class Ticket
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  @Id
  @GeneratedValue (strategy = GenerationType.AUTO)
  private int ticketId;
  private String name;
  private double price;

  //Ticket Associations
  @ManyToMany
  private List<Attendee> attendees;

  @ManyToOne(optional = false)
  private Event event;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(double aPrice, String aName, Event aEvent)
  {
    price = aPrice;
    name = aName;
    attendees = new ArrayList<Attendee>();
    boolean didAddEvent = setEvent(aEvent);
    if (!didAddEvent)
    {
      throw new RuntimeException("Unable to create ticket due to event. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Ticket() {
    attendees = new ArrayList<Attendee>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTicketId(int aTicketId)
  {
    boolean wasSet = false;
    ticketId = aTicketId;
    wasSet = true;
    return wasSet;
  }

  public double getPrice()
  {
    return price;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getTicketId()
  {
    return ticketId;
  }

  public boolean addAttendee(Attendee aAttendee)
  {
    boolean wasAdded = false;
    if (attendees.contains(aAttendee)) { return false; }
    attendees.add(aAttendee);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAttendee(Attendee aAttendee)
  {
    boolean wasRemoved = false;
    attendees.remove(aAttendee);
    wasRemoved = true;
    return wasRemoved;
  }

  public Attendee getAttendee(int index)
  {
    Attendee aAttendee = attendees.get(index);
    return aAttendee;
  }

  public List<Attendee> getAttendees()
  {
    List<Attendee> newAttendees = Collections.unmodifiableList(attendees);
    return attendees;
  }

  /* Code from template association_GetOne */
  public Event getEvent()
  {
    return event;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_SetOneToMany */
  public boolean setEvent(Event aEvent)
  {
    boolean wasSet = false;
    if (aEvent == null)
    {
      return wasSet;
    }

    Event existingEvent = event;
    event = aEvent;
    if (existingEvent != null && !existingEvent.equals(aEvent))
    {
      existingEvent.removeTicket(this);
    }
    event.addTicket(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (attendees != null){
      for(int i=attendees.size(); i > 0; i--)
      {
        Attendee aAttendee = attendees.get(i - 1);
        aAttendee.delete();
      }
      Event placeholderEvent = event;
      this.event = null;
      if(placeholderEvent != null)
      {
        placeholderEvent.removeTicket(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "ticketId" + ":" + getTicketId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getAttendees()!=null?Integer.toHexString(System.identityHashCode(getAttendees())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null");
  }
}