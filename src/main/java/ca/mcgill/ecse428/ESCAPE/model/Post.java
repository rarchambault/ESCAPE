package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;


// line 19 "model.ump"
// line 94 "model.ump"
@Entity
public class Post {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Post Attributes
	private String content;

	private LocalDateTime date;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

  //Post Associations
  @OneToMany(cascade= {CascadeType.ALL})
  private List<Reply> replies;
  @ManyToOne(optional = false,cascade = CascadeType.MERGE)
  private Attendee attendee;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Post(String aContent, Attendee aAttendee, LocalDateTime aDate) {
		content = aContent;
		replies = new ArrayList<Reply>();
		boolean didAddAttendee = setAttendee(aAttendee);
		if (!didAddAttendee) {
			throw new RuntimeException(
					"Unable to create post due to attendee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		date = aDate;
	}

	public Post() {
		replies = new ArrayList<Reply>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setDate(LocalDateTime aDate) {
		boolean wasSet = false;
		date = aDate;
		wasSet = true;
		return wasSet;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public boolean setContent(String aContent) {
		boolean wasSet = false;
		content = aContent;
		wasSet = true;
		return wasSet;
	}

	public boolean setPostId(int aPostId) {
		boolean wasSet = false;
		id = aPostId;
		wasSet = true;
		return wasSet;
	}

	public String getContent() {
		return content;
	}

	public int getPostId() {
		return id;
	}

	/* Code from template association_GetMany */
	public Reply getReply(int index) {
		Reply aReply = replies.get(index);
		return aReply;
	}

	public List<Reply> getReplies() {
		List<Reply> newReplies = Collections.unmodifiableList(replies);
		return newReplies;
	}

	public int numberOfReplies() {
		int number = replies.size();
		return number;
	}

	public boolean hasReplies() {
		boolean has = replies.size() > 0;
		return has;
	}

	public int indexOfReply(Reply aReply) {
		int index = replies.indexOf(aReply);
		return index;
	}

	/* Code from template association_GetMany_clear */
	protected void clear_replies() {
		replies.clear();
	}

	/* Code from template association_GetOne */
	public Attendee getAttendee() {
		return attendee;
	}

	/* Code from template association_GetOne_clear */
	protected void clear_attendee() {
		attendee = null;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfReplies() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Reply addReply(String aContent, int aReplyId, Attendee aAttendee) {
		return new Reply(aContent, aReplyId, this, aAttendee);
	}

	public boolean addReply(Reply aReply) {
		boolean wasAdded = false;
		if (replies.contains(aReply)) {
			return false;
		}
		if (replies.contains(aReply)) {
			return false;
		}
		Post existingPost = aReply.getPost();
		boolean isNewPost = existingPost != null && !this.equals(existingPost);
		if (isNewPost) {
			aReply.setPost(this);
		} else {
			replies.add(aReply);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeReply(Reply aReply) {
		boolean wasRemoved = false;
		// Unable to remove aReply, as it must always have a post
		if (!this.equals(aReply.getPost())) {
			replies.remove(aReply);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addReplyAt(Reply aReply, int index) {
		boolean wasAdded = false;
		if (addReply(aReply)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfReplies()) {
				index = numberOfReplies() - 1;
			}
			replies.remove(aReply);
			replies.add(index, aReply);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveReplyAt(Reply aReply, int index) {
		boolean wasAdded = false;
		if (replies.contains(aReply)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfReplies()) {
				index = numberOfReplies() - 1;
			}
			replies.remove(aReply);
			replies.add(index, aReply);
			wasAdded = true;
		} else {
			wasAdded = addReplyAt(aReply, index);
		}
		return wasAdded;
	}

	/* Code from template association_SetOneToMany */
	public boolean setAttendee(Attendee aAttendee) {
		boolean wasSet = false;
		if (aAttendee == null) {
			return wasSet;
		}

		Attendee existingAttendee = attendee;
		attendee = aAttendee;
		if (existingAttendee != null && !existingAttendee.equals(aAttendee)) {
			existingAttendee.removePost(this);
		}
		attendee.addPost(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		for (int i = replies.size(); i > 0; i--) {
			Reply aReply = replies.get(i - 1);
			aReply.delete();
		}
		Attendee placeholderAttendee = attendee;
		this.attendee = null;
		if (placeholderAttendee != null) {
			placeholderAttendee.removePost(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "content" + ":" + getContent() + "," + "id" + ":" + getPostId() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "userProfile = "
				+ (getAttendee() != null ? Integer.toHexString(System.identityHashCode(getAttendee())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "userProfile = "
				+ (getAttendee() != null ? Integer.toHexString(System.identityHashCode(getAttendee())) : "null");
	}

	private String textBelow;

    public String getTextBelow() {
        return textBelow;
    }

    public void setTextBelow(String textBelow) {
        this.textBelow = textBelow;
    }

}