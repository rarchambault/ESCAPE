package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import jakarta.persistence.*;
import java.util.*;

// line 46 "model.ump"
// line 116 "model.ump"
@Entity
public class Feed
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Feed Attributes
  @Id
  private int feedId;

  //Feed Associations
  @OneToMany(cascade= {CascadeType.ALL})
  @JoinColumn(name="posts")
  private List<Post> posts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Feed(int aFeedId)
  {
    feedId = aFeedId;
    posts = new ArrayList<Post>();
  }

  public Feed() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFeedId(int aFeedId)
  {
    boolean wasSet = false;
    feedId = aFeedId;
    wasSet = true;
    return wasSet;
  }

  public int getFeedId()
  {
    return feedId;
  }
  /* Code from template association_GetMany */
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPosts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPost(Post aPost)
  {
    boolean wasAdded = false;
    if (posts.contains(aPost)) { return false; }
    posts.add(aPost);
    if (aPost.indexOfFeed(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPost.addFeed(this);
      if (!wasAdded)
      {
        posts.remove(aPost);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePost(Post aPost)
  {
    boolean wasRemoved = false;
    if (!posts.contains(aPost))
    {
      return wasRemoved;
    }

    int oldIndex = posts.indexOf(aPost);
    posts.remove(oldIndex);
    if (aPost.indexOfFeed(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPost.removeFeed(this);
      if (!wasRemoved)
      {
        posts.add(oldIndex,aPost);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPostAt(Post aPost, int index)
  {  
    boolean wasAdded = false;
    if(addPost(aPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPosts()) { index = numberOfPosts() - 1; }
      posts.remove(aPost);
      posts.add(index, aPost);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePostAt(Post aPost, int index)
  {
    boolean wasAdded = false;
    if(posts.contains(aPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPosts()) { index = numberOfPosts() - 1; }
      posts.remove(aPost);
      posts.add(index, aPost);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPostAt(aPost, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Post> copyOfPosts = new ArrayList<Post>(posts);
    posts.clear();
    for(Post aPost : copyOfPosts)
    {
      aPost.removeFeed(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "feedId" + ":" + getFeedId()+ "]";
  }
}