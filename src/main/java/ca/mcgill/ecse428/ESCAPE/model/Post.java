/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 19 "model.ump"
// line 94 "model.ump"
public class Post
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Post Attributes
  private String content;
  private int postId;

  //Post Associations
  private List<Reply> replies;
  private UserProfile userProfile;
  private List<Feed> feeds;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Post(String aContent, int aPostId, UserProfile aUserProfile)
  {
    content = aContent;
    postId = aPostId;
    replies = new ArrayList<Reply>();
    boolean didAddUserProfile = setUserProfile(aUserProfile);
    if (!didAddUserProfile)
    {
      throw new RuntimeException("Unable to create post due to userProfile. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    feeds = new ArrayList<Feed>();
  }

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
  /* Code from template association_GetOne_relatedSpecialization */
  public UserProfile getUserProfile_OneUserProfile()
  {
    return (UserProfile)userProfile;
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
  public UserProfile getUserProfile()
  {
    return userProfile;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_userProfile()
  {
    userProfile = null;
  }
  /* Code from template association_GetMany_relatedSpecialization */
  public Reply getReply_Reply(int index)
  {
    Reply aReply = (Reply)replies.get(index);
    return aReply;
  }

  /* required for Java 7. */
  @SuppressWarnings("unchecked")
  public List<Reply> getReplies_Reply()
  {
    List<? extends Reply> newReplies = Collections.unmodifiableList(replies);
    return (List<Reply>)newReplies;
  }
  /* Code from template association_GetMany */
  public Feed getFeed(int index)
  {
    Feed aFeed = feeds.get(index);
    return aFeed;
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
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_SetOneToMany_relatedSpecialization */
  public boolean setUserProfile_UserProfile(UserProfile aUserProfile)
  {
    boolean wasSet = false;
    if (aUserProfile == null)
    {
      return wasSet;
    }

    UserProfile existingUserProfile = (UserProfile)userProfile;
    userProfile = aUserProfile;
    if (existingUserProfile != null && !existingUserProfile.equals(aUserProfile))
    {
      existingUserProfile.removePost(this);
    }
    userProfile.addPost(this);
    wasSet = true;
    return wasSet;
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
      existingUserProfile.removePost(this);
    }
    userProfile.addPost(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_MinimumNumberOfMethod_relatedSpecialization */
  public static int minimumNumberOfReplies_Reply()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne_relatedSpecialization */
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
  /* Code from template association_AddIndexControlFunctions_relatedSpecialization */
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
    UserProfile placeholderUserProfile = userProfile;
    this.userProfile = null;
    if(placeholderUserProfile != null)
    {
      placeholderUserProfile.removePost(this);
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
            "  " + "userProfile = "+(getUserProfile()!=null?Integer.toHexString(System.identityHashCode(getUserProfile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getUserProfile()!=null?Integer.toHexString(System.identityHashCode(getUserProfile())):"null");
  }
}