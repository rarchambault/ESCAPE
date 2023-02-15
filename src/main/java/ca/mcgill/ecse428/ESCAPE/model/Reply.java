/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 27 "model.ump"
// line 101 "model.ump"
public class Reply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reply Attributes
  private String content;
  private int replyId;

  //Reply Associations
  private Post post;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reply(String aContent, int aReplyId, Post aPost)
  {
    content = aContent;
    replyId = aReplyId;
    boolean didAddPost = setPost(aPost);
    if (!didAddPost)
    {
      throw new RuntimeException("Unable to create reply due to post. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setReplyId(int aReplyId)
  {
    boolean wasSet = false;
    replyId = aReplyId;
    wasSet = true;
    return wasSet;
  }

  public String getContent()
  {
    return content;
  }

  public int getReplyId()
  {
    return replyId;
  }
  /* Code from template association_GetOne_relatedSpecialization */
  public Post getPost_OnePost()
  {
    return (Post)post;
  } 
  /* Code from template association_GetOne */
  public Post getPost()
  {
    return post;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_post()
  {
    post = null;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_SetOneToMany_relatedSpecialization */
  public boolean setPost_Post(Post aPost)
  {
    boolean wasSet = false;
    if (aPost == null)
    {
      return wasSet;
    }

    Post existingPost = (Post)post;
    post = aPost;
    if (existingPost != null && !existingPost.equals(aPost))
    {
      existingPost.removeReply(this);
    }
    post.addReply(this);
    wasSet = true;
    return wasSet;
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