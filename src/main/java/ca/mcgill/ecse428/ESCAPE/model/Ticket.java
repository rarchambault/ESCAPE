package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
  private int ticketId;

  //Ticket Associations
  @ManyToOne
  private Attendee attendee;

  @ManyToOne(optional = false)
  private Event event;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(int aTicketId, Attendee aAttendee, Event aEvent)
  {
    ticketId = aTicketId;
    boolean didAddUserProfile = setAttendee(aAttendee);
    if (!didAddUserProfile)
    {
      throw new RuntimeException("Unable to create ticket due to userProfile. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddEvent = setEvent(aEvent);
    if (!didAddEvent)
    {
      throw new RuntimeException("Unable to create ticket due to event. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Ticket() {}

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

  public int getTicketId()
  {
    return ticketId;
  }
  /* Code from template association_GetOne */
  public Attendee getAttendee()
  {
    return attendee;
  }
  /* Code from template association_GetOne_relatedSpecialization */
  public Event getEvent_OneEvent()
  {
    return (Event)event;
  } 
  /* Code from template association_GetOne */
  public Event getEvent()
  {
    return event;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_event()
  {
    event = null;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAttendee(Attendee aAttendee)
  {
    boolean wasSet = false;
    if (aAttendee == null)
    {
      return wasSet;
    }

    Attendee existingAttendee = attendee;
    attendee = aAttendee;
    if (existingAttendee != null && !existingAttendee.equals(aAttendee))
    {
      existingAttendee.removeTicket(this);
    }
    attendee.addTicket(this);
    wasSet = true;
    return wasSet;
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
    Attendee placeholderAttendee= attendee;
    this.attendee = null;
    if(placeholderAttendee != null)
    {
      placeholderAttendee.removeTicket(this);
    }
    Event placeholderEvent = event;
    this.event = null;
    if(placeholderEvent != null)
    {
      placeholderEvent.removeTicket(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "ticketId" + ":" + getTicketId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getAttendee()!=null?Integer.toHexString(System.identityHashCode(getAttendee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null");
  }
}