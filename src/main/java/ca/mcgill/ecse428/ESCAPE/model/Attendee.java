package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.*;

// line 15 "model.ump"
// line 89 "model.ump"
@Entity
public class Attendee extends UserProfile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  //Attendee Associations
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="posts")
  private List<Post> posts;
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="events")
  private List<Event> events;
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="tickets")
  private List<Ticket> tickets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Attendee(String aName, String aEmail, String aPassword, String aPhoto, int aUserId)
  {
    super(aName, aEmail, aPassword, aPhoto, aUserId);
    posts = new ArrayList<Post>();
    events = new ArrayList<Event>();
    tickets = new ArrayList<Ticket>();
  }

  public Attendee() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean addPost(Post aPost)
  {
    boolean wasAdded = false;
    if (posts.contains(aPost)) { return false; }
    posts.add(aPost);
    aPost.setAttendee(this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePost(Post aPost)
  {
    boolean wasRemoved = false;
    aPost.delete();
    posts.remove(aPost);
    wasRemoved = true;
    return wasRemoved;
  }

  public Post getPost(int index)
  {
    Post aPost = posts.get(index);
    return aPost;
  }

  public List<Post> getPosts()
  {
    List<Post> newPosts = Collections.unmodifiableList(posts);
    return newPosts;
  }

  public int numberOfPosts()
  {
    int number = posts.size();
    return number;
  }

  public boolean hasPosts()
  {
    boolean has = posts.size() > 0;
    return has;
  }

  public int indexOfPost(Post aPost)
  {
    int index = posts.indexOf(aPost);
    return index;
  }
  /* Code from template association_GetMany_clear */
  protected void clear_posts()
  {
    posts.clear();
  }
  /* Code from template association_GetMany */
  public Event getEvent(int index)
  {
    Event aEvent = events.get(index);
    return aEvent;
  }

  public List<Event> getEvents()
  {
    List<Event> newEvents = Collections.unmodifiableList(events);
    return newEvents;
  }

  public int numberOfEvents()
  {
    int number = events.size();
    return number;
  }

  public boolean hasEvents()
  {
    boolean has = events.size() > 0;
    return has;
  }

  public int indexOfEvent(Event aEvent)
  {
    int index = events.indexOf(aEvent);
    return index;
  }
  /* Code from template association_GetMany_clear */
  protected void clear_events()
  {
    events.clear();
  }
  /* Code from template association_GetMany_relatedSpecialization */
  public Post getPost_Post(int index)
  {
    Post aPost = (Post)posts.get(index);
    return aPost;
  }

  /* Code from template association_GetMany */
  public Ticket getTicket(int index)
  {
    Ticket aTicket = tickets.get(index);
    return aTicket;
  }

  public List<Ticket> getTickets()
  {
    List<Ticket> newTickets = Collections.unmodifiableList(tickets);
    return newTickets;
  }

  public int numberOfTickets()
  {
    int number = tickets.size();
    return number;
  }

  public boolean hasTickets()
  {
    boolean has = tickets.size() > 0;
    return has;
  }

  public int indexOfTicket(Ticket aTicket)
  {
    int index = tickets.indexOf(aTicket);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPosts()
  {
    return 0;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEvents()
  {
    return 0;
  }

  public boolean addEvent(Event aEvent)
  {
    boolean wasAdded = false;
    if (events.contains(aEvent)) { return false; }
    aEvent.addAttendee(this);
    events.add(aEvent);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEvent(Event aEvent)
  {
    boolean wasRemoved = false;
    aEvent.removeAttendee(this);
    events.remove(aEvent);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEventAt(Event aEvent, int index)
  {
    boolean wasAdded = false;
    if(addEvent(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEventAt(Event aEvent, int index)
  {
    boolean wasAdded = false;
    if(events.contains(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    }
    else
    {
      wasAdded = addEventAt(aEvent, index);
    }
    return wasAdded;
  }

  /* Code from template association_AddManyToOne */
  public Ticket addTicket(int aTicketId, Event aEvent)
  {
    return new Ticket(aTicketId, this, aEvent);
  }

  public boolean addTicket(Ticket aTicket)
  {
    boolean wasAdded = false;
    if (tickets.contains(aTicket)) { return false; }
    Attendee existingAttendee = aTicket.getAttendee();
    boolean isNewAttendee = existingAttendee != null && !this.equals(existingAttendee);
    if (isNewAttendee)
    {
      aTicket.setAttendee(this);
    }
    else
    {
      tickets.add(aTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicket(Ticket aTicket)
  {
    boolean wasRemoved = false;
    tickets.remove(aTicket);
    aTicket.delete();
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketAt(Ticket aTicket, int index)
  {
    boolean wasAdded = false;
    if(addTicket(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketAt(Ticket aTicket, int index)
  {
    boolean wasAdded = false;
    if(tickets.contains(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    }
    else
    {
      wasAdded = addTicketAt(aTicket, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    super.delete();
    for(int i=posts.size(); i > 0; i--)
    {
      Post aPost = posts.get(i - 1);
      aPost.delete();
    }
    for(int i=events.size(); i > 0; i--)
    {
      Event aEvent = events.get(i - 1);
      aEvent.delete();
    }
    for(int i=tickets.size(); i > 0; i--)
    {
      Ticket aTicket = tickets.get(i - 1);
      aTicket.delete();
    }
  }

}