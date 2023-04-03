package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// line 27 "model.ump"
// line 101 "model.ump"
@Entity
public class Reply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reply Attributes
  private String content;
  @Id
  private int replyId;

  //Reply Associations
  @ManyToOne(optional = false)
  private Post post;

  @ManyToOne(optional = false,cascade = CascadeType.MERGE)
  private Attendee attendee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reply(String aContent, int aReplyId, Post aPost, Attendee aAttendee)
  {
    content = aContent;
    replyId = aReplyId;
    boolean didAddPost = setPost(aPost);
    if (!didAddPost)
    {
      throw new RuntimeException("Unable to create reply due to post. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAttendee = setAttendee(aAttendee);
    if (!didAddAttendee) {
      throw new RuntimeException(
              "Unable to create post due to attendee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Reply() {}

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

  public boolean setReplyId(int aReplyId)
  {
    boolean wasSet = false;
    replyId = aReplyId;
    wasSet = true;
    return wasSet;
  }

  public boolean setAttendee(Attendee aAttendee) {
    boolean wasSet = false;
    if (aAttendee == null) {
      return wasSet;
    }

    Attendee existingAttendee = attendee;
    attendee = aAttendee;
    wasSet = true;
    return wasSet;
  }

  public Attendee getAttendee() {
    return attendee;
  }

  public String getContent()
  {
    return content;
  }

  public int getReplyId()
  {
    return replyId;
  }

  public Post getPost()
  {
    return post;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_post()
  {
    post = null;
  }

  /* Code from template association_SetOneToMany */
  public boolean setPost(Post aPost)
  {
    boolean wasSet = false;
    if (aPost == null)
    {
      return wasSet;
    }

    Post existingPost = post;
    post = aPost;
    if (existingPost != null && !existingPost.equals(aPost))
    {
      existingPost.removeReply(this);
    }
    post.addReply(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Post placeholderPost = post;
    this.post = null;
    if(placeholderPost != null)
    {
      placeholderPost.removeReply(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "content" + ":" + getContent()+ "," +
            "replyId" + ":" + getReplyId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "post = "+(getPost()!=null?Integer.toHexString(System.identityHashCode(getPost())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "post = "+(getPost()!=null?Integer.toHexString(System.identityHashCode(getPost())):"null");
  }
}