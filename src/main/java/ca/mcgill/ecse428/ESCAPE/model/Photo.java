package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// line 59 "model.ump"
// line 129 "model.ump"
@Entity
public class Photo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Photo Attributes
  private String photoPath;
  @Id
  private int photoId;

  //Photo Associations
  @ManyToOne(optional = false)
  private PhotoGallery photoGallery;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Photo(String aPhotoPath, int aPhotoId, PhotoGallery aPhotoGallery)
  {
    photoPath = aPhotoPath;
    photoId = aPhotoId;
    boolean didAddPhotoGallery = setPhotoGallery(aPhotoGallery);
    if (!didAddPhotoGallery)
    {
      throw new RuntimeException("Unable to create photo due to photoGallery. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Photo() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhotoPath(String aPhotoPath)
  {
    boolean wasSet = false;
    photoPath = aPhotoPath;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhotoId(int aPhotoId)
  {
    boolean wasSet = false;
    photoId = aPhotoId;
    wasSet = true;
    return wasSet;
  }

  public String getPhotoPath()
  {
    return photoPath;
  }

  public int getPhotoId()
  {
    return photoId;
  }
  /* Code from template association_GetOne */
  public PhotoGallery getPhotoGallery()
  {
    return photoGallery;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPhotoGallery(PhotoGallery aPhotoGallery)
  {
    boolean wasSet = false;
    if (aPhotoGallery == null)
    {
      return wasSet;
    }

    PhotoGallery existingPhotoGallery = photoGallery;
    photoGallery = aPhotoGallery;
    if (existingPhotoGallery != null && !existingPhotoGallery.equals(aPhotoGallery))
    {
      existingPhotoGallery.removePhoto(this);
    }
    photoGallery.addPhoto(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    PhotoGallery placeholderPhotoGallery = photoGallery;
    this.photoGallery = null;
    if(placeholderPhotoGallery != null)
    {
      placeholderPhotoGallery.removePhoto(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "photoPath" + ":" + getPhotoPath()+ "," +
            "photoId" + ":" + getPhotoId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "photoGallery = "+(getPhotoGallery()!=null?Integer.toHexString(System.identityHashCode(getPhotoGallery())):"null");
  }
}