package ca.mcgill.ecse428.ESCAPE.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 52 "model.ump"
// line 122 "model.ump"
public class Ticket
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  private int ticketId;

  //Ticket Associations
  private UserProfile userProfile;
  private Event event;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(int aTicketId, UserProfile aUserProfile, Event aEvent)
  {
    ticketId = aTicketId;
    boolean didAddUserProfile = setUserProfile(aUserProfile);
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
  public UserProfile getUserProfile()
  {
    return userProfile;
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
  public boolean setUserProfile(UserProfile aUserProfile)
  {
    boolean wasSet = false;
    if (aUserProfile == null)
    {
      return wasSet;
    }

    UserProfile existingUserProfile = userProfile;
    userProfile = aUserProfile;
    if (existingUserProfile != null && !existingUserProfile.equals(aUserProfile))
    {
      existingUserProfile.removeTicket(this);
    }
    userProfile.addTicket(this);
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
    UserProfile placeholderUserProfile = userProfile;
    this.userProfile = null;
    if(placeholderUserProfile != null)
    {
      placeholderUserProfile.removeTicket(this);
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
            "  " + "userProfile = "+(getUserProfile()!=null?Integer.toHexString(System.identityHashCode(getUserProfile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null");
  }
}