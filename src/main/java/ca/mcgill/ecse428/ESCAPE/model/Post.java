package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.util.*;

// line 19 "model.ump"
// line 94 "model.ump"
@Entity
public class Post
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Post Attributes
  private String content;
  @Id
  private int postId;

  //Post Associations
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="replies")
  private List<Reply> replies;
  @OneToOne(optional = false)
  private Attendee attendee;
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="feeds")
  private List<Feed> feeds;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Post(String aContent, int aPostId, Attendee aAttendee)
  {
    content = aContent;
    postId = aPostId;
    replies = new ArrayList<Reply>();
    boolean didAddAttendee = setAttendee(aAttendee);
    if (!didAddAttendee)
    {
      throw new RuntimeException("Unable to create post due to attendee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    feeds = new ArrayList<Feed>();
  }

  public Post() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostId(int aPostId)
  {
    boolean wasSet = false;
    postId = aPostId;
    wasSet = true;
    return wasSet;
  }

  public String getContent()
  {
    return content;
  }

  public int getPostId()
  {
    return postId;
  }
  /* Code from template association_GetMany */
  public Reply getReply(int index)
  {
    Reply aReply = replies.get(index);
    return aReply;
  }

  public List<Reply> getReplies()
  {
    List<Reply> newReplies = Collections.unmodifiableList(replies);
    return newReplies;
  }

  public int numberOfReplies()
  {
    int number = replies.size();
    return number;
  }

  public boolean hasReplies()
  {
    boolean has = replies.size() > 0;
    return has;
  }

  public int indexOfReply(Reply aReply)
  {
    int index = replies.indexOf(aReply);
    return index;
  }
  /* Code from template association_GetMany_clear */
  protected void clear_replies()
  {
    replies.clear();
  }
  /* Code from template association_GetOne */
  public Attendee getAttendee()
  {
    return attendee;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_attendee()
  {
    attendee = null;
  }

  public List<Feed> getFeeds()
  {
    List<Feed> newFeeds = Collections.unmodifiableList(feeds);
    return newFeeds;
  }

  public int numberOfFeeds()
  {
    int number = feeds.size();
    return number;
  }

  public boolean hasFeeds()
  {
    boolean has = feeds.size() > 0;
    return has;
  }

  public int indexOfFeed(Feed aFeed)
  {
    int index = feeds.indexOf(aFeed);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReplies()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reply addReply(String aContent, int aReplyId)
  {
    return new Reply(aContent, aReplyId, this);
  }

  public boolean addReply(Reply aReply)
  {
    boolean wasAdded = false;
    if (replies.contains(aReply)) { return false; }
    if (replies.contains(aReply)) { return false; }
    Post existingPost = aReply.getPost();
    boolean isNewPost = existingPost != null && !this.equals(existingPost);
    if (isNewPost)
    {
      aReply.setPost(this);
    }
    else
    {
      replies.add(aReply);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReply(Reply aReply)
  {
    boolean wasRemoved = false;
    //Unable to remove aReply, as it must always have a post
    if (!this.equals(aReply.getPost()))
    {
      replies.remove(aReply);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReplyAt(Reply aReply, int index)
  {  
    boolean wasAdded = false;
    if(addReply(aReply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReplies()) { index = numberOfReplies() - 1; }
      replies.remove(aReply);
      replies.add(index, aReply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReplyAt(Reply aReply, int index)
  {
    boolean wasAdded = false;
    if(replies.contains(aReply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReplies()) { index = numberOfReplies() - 1; }
      replies.remove(aReply);
      replies.add(index, aReply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReplyAt(aReply, index);
    }
    return wasAdded;
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
      existingAttendee.removePost(this);
    }
    attendee.addPost(this);
    wasSet = true;
    return wasSet;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFeeds()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addFeed(Feed aFeed)
  {
    boolean wasAdded = false;
    if (feeds.contains(aFeed)) { return false; }
    feeds.add(aFeed);
    if (aFeed.indexOfPost(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFeed.addPost(this);
      if (!wasAdded)
      {
        feeds.remove(aFeed);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeFeed(Feed aFeed)
  {
    boolean wasRemoved = false;
    if (!feeds.contains(aFeed))
    {
      return wasRemoved;
    }

    int oldIndex = feeds.indexOf(aFeed);
    feeds.remove(oldIndex);
    if (aFeed.indexOfPost(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFeed.removePost(this);
      if (!wasRemoved)
      {
        feeds.add(oldIndex,aFeed);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFeedAt(Feed aFeed, int index)
  {  
    boolean wasAdded = false;
    if(addFeed(aFeed))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFeeds()) { index = numberOfFeeds() - 1; }
      feeds.remove(aFeed);
      feeds.add(index, aFeed);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFeedAt(Feed aFeed, int index)
  {
    boolean wasAdded = false;
    if(feeds.contains(aFeed))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFeeds()) { index = numberOfFeeds() - 1; }
      feeds.remove(aFeed);
      feeds.add(index, aFeed);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFeedAt(aFeed, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=replies.size(); i > 0; i--)
    {
      Reply aReply = replies.get(i - 1);
      aReply.delete();
    }
    Attendee placeholderAttendee = attendee;
    this.attendee = null;
    if(placeholderAttendee != null)
    {
      placeholderAttendee.removePost(this);
    }
    ArrayList<Feed> copyOfFeeds = new ArrayList<Feed>(feeds);
    feeds.clear();
    for(Feed aFeed : copyOfFeeds)
    {
      aFeed.removePost(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "content" + ":" + getContent()+ "," +
            "postId" + ":" + getPostId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getAttendee()!=null?Integer.toHexString(System.identityHashCode(getAttendee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getAttendee()!=null?Integer.toHexString(System.identityHashCode(getAttendee())):"null");
  }
}