package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.util.*;

// line 34 "model.ump"
// line 107 "model.ump"

@Entity
public class Event {

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Attributes
  private String name;
  private String description;
  private double ticketPrice;
  @Id
  private int id;

  //Event Associations
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="tickets")
  private List<Ticket> tickets;
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="photoGalleries")
  private List<PhotoGallery> photoGalleries;
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="attendees")
  private List<Attendee> attendees;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(String aName, String aDescription, double aTicketPrice, int aEventId, UserProfile aUserProfile) {
    name = aName;
    description = aDescription;
    ticketPrice = aTicketPrice;
    id = aEventId;
    tickets = new ArrayList<Ticket>();
    photoGalleries = new ArrayList<PhotoGallery>();
    attendees = new ArrayList<Attendee>();
  }

  public Event() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName) {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription) {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setTicketPrice(double aTicketPrice) {
    boolean wasSet = false;
    ticketPrice = aTicketPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setEventId(int aEventId) {
    boolean wasSet = false;
    id = aEventId;
    wasSet = true;
    return wasSet;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getTicketPrice() {
    return ticketPrice;
  }

  public int getId() {
    return id;
  }

  /* Code from template association_GetMany */
  public Ticket getTicket(int index) {
    Ticket aTicket = tickets.get(index);
    return aTicket;
  }

  public List<Ticket> getTickets() {
    List<Ticket> newTickets = Collections.unmodifiableList(tickets);
    return newTickets;
  }

  public int numberOfTickets() {
    int number = tickets.size();
    return number;
  }

  public boolean hasTickets() {
    boolean has = tickets.size() > 0;
    return has;
  }

  public int indexOfTicket(Ticket aTicket) {
    int index = tickets.indexOf(aTicket);
    return index;
  }

  /* Code from template association_GetMany_clear */
  protected void clear_tickets() {
    tickets.clear();
  }

  /* Code from template association_GetMany */
  public PhotoGallery getPhotoGallery(int index) {
    PhotoGallery aPhotoGallery = photoGalleries.get(index);
    return aPhotoGallery;
  }

  public List<PhotoGallery> getPhotoGalleries() {
    List<PhotoGallery> newPhotoGalleries = Collections.unmodifiableList(photoGalleries);
    return newPhotoGalleries;
  }

  public int numberOfPhotoGalleries() {
    int number = photoGalleries.size();
    return number;
  }

  public boolean hasPhotoGalleries() {
    boolean has = photoGalleries.size() > 0;
    return has;
  }

  public int indexOfPhotoGallery(PhotoGallery aPhotoGallery) {
    int index = photoGalleries.indexOf(aPhotoGallery);
    return index;
  }

  public Attendee getAttendee(int index) {
    Attendee aAttendee = attendees.get(index);
    return aAttendee;
  }

  public List<Attendee> getAttendees() {
    List<Attendee> newAttendees = Collections.unmodifiableList(attendees);
    return newAttendees;
  }

  public int numberOfAttendees() {
    int number = attendees.size();
    return number;
  }

  public boolean hasUAttendees() {
    boolean has = attendees.size() > 0;
    return has;
  }

  public int indexOfAttendee(Attendee aAttendee) {
    int index = attendees.indexOf(aAttendee);
    return index;
  }

  /* Code from template association_GetMany_clear */
  protected void clear_attendees() {
    attendees.clear();
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTickets() {
    return 0;
  }

  /* Code from template association_AddManyToOne */
  public Ticket addTicket(int aTicketId, double aPrice, String aName, Attendee aAttendee) {
    return new Ticket(aTicketId, aPrice, aName, aAttendee, this);
  }

  public boolean addTicket(Ticket aTicket) {
    boolean wasAdded = false;
    if (tickets.contains(aTicket)) {
      return false;
    }
    Event existingEvent = aTicket.getEvent();
    boolean isNewEvent = existingEvent != null && !this.equals(existingEvent);
    if (isNewEvent) {
      aTicket.setEvent(this);
    } else {
      tickets.add(aTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicket(Ticket aTicket) {
    boolean wasRemoved = false;
    //Unable to remove aTicket, as it must always have a event
    if (!this.equals(aTicket.getEvent())) {
      tickets.remove(aTicket);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketAt(Ticket aTicket, int index) {
    boolean wasAdded = false;
    if (addTicket(aTicket)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfTickets()) {
        index = numberOfTickets() - 1;
      }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketAt(Ticket aTicket, int index) {
    boolean wasAdded = false;
    if (tickets.contains(aTicket)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfTickets()) {
        index = numberOfTickets() - 1;
      }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    } else {
      wasAdded = addTicketAt(aTicket, index);
    }
    return wasAdded;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPhotoGalleries() {
    return 0;
  }

  /* Code from template association_AddManyToOne */
  public PhotoGallery addPhotoGallery(int aGalleryId) {
    return new PhotoGallery(aGalleryId, this);
  }

  public boolean addPhotoGallery(PhotoGallery aPhotoGallery) {
    boolean wasAdded = false;
    if (photoGalleries.contains(aPhotoGallery)) {
      return false;
    }
    Event existingEvent = aPhotoGallery.getEvent();
    boolean isNewEvent = existingEvent != null && !this.equals(existingEvent);
    if (isNewEvent) {
      aPhotoGallery.setEvent(this);
    } else {
      photoGalleries.add(aPhotoGallery);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePhotoGallery(PhotoGallery aPhotoGallery) {
    boolean wasRemoved = false;
    //Unable to remove aPhotoGallery, as it must always have a event
    if (!this.equals(aPhotoGallery.getEvent())) {
      photoGalleries.remove(aPhotoGallery);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_AddIndexControlFunctions */
  public boolean addPhotoGalleryAt(PhotoGallery aPhotoGallery, int index) {
    boolean wasAdded = false;
    if (addPhotoGallery(aPhotoGallery)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfPhotoGalleries()) {
        index = numberOfPhotoGalleries() - 1;
      }
      photoGalleries.remove(aPhotoGallery);
      photoGalleries.add(index, aPhotoGallery);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePhotoGalleryAt(PhotoGallery aPhotoGallery, int index) {
    boolean wasAdded = false;
    if (photoGalleries.contains(aPhotoGallery)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfPhotoGalleries()) {
        index = numberOfPhotoGalleries() - 1;
      }
      photoGalleries.remove(aPhotoGallery);
      photoGalleries.add(index, aPhotoGallery);
      wasAdded = true;
    } else {
      wasAdded = addPhotoGalleryAt(aPhotoGallery, index);
    }
    return wasAdded;
  }

  //String aName, String aEmail, String aPassword, String aPhoto, int aUserId

  public boolean addAttendee(Attendee aAttendee) {
    boolean wasAdded = false;
    if (attendees.contains(aAttendee)) {
      return false;
    }
    attendees.add(aAttendee);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAttendee(Attendee aAttendee) {
    boolean wasRemoved = false;
    attendees.remove(aAttendee);
    wasRemoved = true;
    return wasRemoved;
  }

  /* Code from template association_set_specialization_reqCommonCode */
  public void delete() {
    for (int i = tickets.size(); i > 0; i--) {
      Ticket aTicket = tickets.get(i - 1);
      aTicket.delete();
    }
    for (int i = photoGalleries.size(); i > 0; i--) {
      PhotoGallery aPhotoGallery = photoGalleries.get(i - 1);
      aPhotoGallery.delete();
    }
    for (int i = attendees.size(); i > 0; i--) {
      UserProfile aUserProfile = attendees.get(i - 1);
      aUserProfile.delete();
    }
  }

public void setDate(String string) {
}
}