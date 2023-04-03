package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.util.*;

// line 15 "model.ump"
// line 89 "model.ump"
@Entity
public class Attendee extends UserProfile {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------
	// Attendee Associations
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Post> posts;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Ticket> tickets;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Attendee(String aName, String aEmail, String aPassword) {
		super(aName, aEmail, aPassword);
		posts = new ArrayList<Post>();
		tickets = new ArrayList<Ticket>();
	}

	public Attendee() {
		posts = new ArrayList<Post>();
		tickets = new ArrayList<Ticket>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean addPost(Post aPost) {
		boolean wasAdded = false;
		if (posts.contains(aPost)) {
			return false;
		}
		posts.add(aPost);
		aPost.setAttendee(this);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removePost(Post aPost) {
		boolean wasRemoved = false;
		aPost.delete();
		posts.remove(aPost);
		wasRemoved = true;
		return wasRemoved;
	}

	public Post getPost(int index) {
		Post aPost = posts.get(index);
		return aPost;
	}

	public List<Post> getPosts() {
		List<Post> newPosts = Collections.unmodifiableList(posts);
		return newPosts;
	}

	public int numberOfPosts() {
		int number = posts.size();
		return number;
	}

	public boolean hasPosts() {
		boolean has = posts.size() > 0;
		return has;
	}

	public int indexOfPost(Post aPost) {
		int index = posts.indexOf(aPost);
		return index;
	}

	/* Code from template association_GetMany_clear */
	protected void clear_posts() {
		posts.clear();
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

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfPosts() {
		return 0;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfEvents() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Ticket addTicket(double aPrice, String aName, Event aEvent) {
		return new Ticket(aPrice, aName, aEvent);
	}

	public boolean addTicket(Ticket aTicket) {
		boolean wasAdded = false;
		if (tickets.contains(aTicket)) {
			return false;
		}
		List<Attendee> existingAttendees = aTicket.getAttendees();
		boolean isNewAttendee = existingAttendees != null && !existingAttendees.contains(this);
		if (isNewAttendee) {
			aTicket.addAttendee(this);
		} else {
			tickets.add(aTicket);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeTicket(Ticket aTicket) {
		boolean wasRemoved = false;
		tickets.remove(aTicket);
		aTicket.delete();
		wasRemoved = true;
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

	public void delete() {
		super.delete();
		for (int i = posts.size(); i > 0; i--) {
			Post aPost = posts.get(i - 1);
			aPost.delete();
		}
		for (int i = tickets.size(); i > 0; i--) {
			Ticket aTicket = tickets.get(i - 1);
			aTicket.delete();
		}
	}
}
