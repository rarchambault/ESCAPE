package ca.mcgill.ecse428.ESCAPE.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Event {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Event Attributes
	private String name;
	private String description;

	private String location;

	private String picture_path;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int capacity;
	private LocalDateTime startTime;

	// Event Associations
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Ticket> tickets;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PhotoGallery> photoGalleries;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Event(String aName, String aDescription, int aCapacity, LocalDateTime aStartTime, String aLocation) {
		name = aName;
		description = aDescription;
		capacity = aCapacity;
		startTime = aStartTime;
		tickets = new ArrayList<Ticket>();
		photoGalleries = new ArrayList<PhotoGallery>();
		location = aLocation;
		picture_path = "";
	}

	public Event() {
		tickets = new ArrayList<Ticket>();
		photoGalleries = new ArrayList<PhotoGallery>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

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

	public boolean setLocation(String aLocation) {
		boolean wasSet = false;
		location = aLocation;
		wasSet = true;
		return wasSet;
	}

	public boolean setEventId(int aEventId) {
		boolean wasSet = false;
		id = aEventId;
		wasSet = true;
		return wasSet;
	}

	public boolean setCapacity(int aCapacity) {
		boolean wasSet = false;
		capacity = aCapacity;
		wasSet = true;
		return wasSet;
	}

	public boolean setStartTime(LocalDateTime aStartTime) {
		boolean wasSet = false;
		startTime = aStartTime;
		wasSet = true;
		return wasSet;
	}

	public boolean set_picture_path(String aPicture_path) {
		boolean wasSet = false;
		picture_path = aPicture_path;
		wasSet = true;
		return wasSet;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public int getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public String get_picture_path() {
		return picture_path;
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

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfTickets() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Ticket addTicket(double aPrice, String aName) {
		return new Ticket(aPrice, aName,this);
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
		// Unable to remove aTicket, as it must always have a event
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
		// Unable to remove aPhotoGallery, as it must always have a event
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
	}

	public void deleteStartTime() {
		this.startTime = null;
	}

	public void deleteCapacity() {
		this.capacity = (Integer) null;
	}

	public void deleteDescription() {
		this.description = null;
	}
}