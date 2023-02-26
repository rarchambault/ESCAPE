package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.util.*;

@Entity
// line 68 "model.ump"
// line 136 "model.ump"
public class PhotoGallery
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PhotoGallery Attributes
  @Id
  private int galleryId;

  //PhotoGallery Associations
  @ManyToOne(optional = false)
  private Event event;
  @OneToMany(cascade= {CascadeType.ALL})
  private List<Photo> photos;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PhotoGallery(int aGalleryId, Event aEvent)
  {
    galleryId = aGalleryId;
    boolean didAddEvent = setEvent(aEvent);
    if (!didAddEvent)
    {
      throw new RuntimeException("Unable to create photoGallery due to event. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    photos = new ArrayList<Photo>();
  }

  public PhotoGallery() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGalleryId(int aGalleryId)
  {
    boolean wasSet = false;
    galleryId = aGalleryId;
    wasSet = true;
    return wasSet;
  }

  public int getGalleryId()
  {
    return galleryId;
  }
  /* Code from template association_GetOne */
  public Event getEvent()
  {
    return event;
  }
  /* Code from template association_GetMany */
  public Photo getPhoto(int index)
  {
    Photo aPhoto = photos.get(index);
    return aPhoto;
  }

  public List<Photo> getPhotos()
  {
    List<Photo> newPhotos = Collections.unmodifiableList(photos);
    return newPhotos;
  }

  public int numberOfPhotos()
  {
    int number = photos.size();
    return number;
  }

  public boolean hasPhotos()
  {
    boolean has = photos.size() > 0;
    return has;
  }

  public int indexOfPhoto(Photo aPhoto)
  {
    int index = photos.indexOf(aPhoto);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEvent(Event aEvent)
  {
    boolean wasSet = false;
    if (aEvent == null)
    {
      return wasSet;
    }

    Event existingEvent = event;
    event = aEvent;
    if (existingEvent != null && !existingEvent.equals(aEvent))
    {
      existingEvent.removePhotoGallery(this);
    }
    event.addPhotoGallery(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPhotos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Photo addPhoto(String aPhotoPath, int aPhotoId)
  {
    return new Photo(aPhotoPath, aPhotoId, this);
  }

  public boolean addPhoto(Photo aPhoto)
  {
    boolean wasAdded = false;
    if (photos.contains(aPhoto)) { return false; }
    PhotoGallery existingPhotoGallery = aPhoto.getPhotoGallery();
    boolean isNewPhotoGallery = existingPhotoGallery != null && !this.equals(existingPhotoGallery);
    if (isNewPhotoGallery)
    {
      aPhoto.setPhotoGallery(this);
    }
    else
    {
      photos.add(aPhoto);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePhoto(Photo aPhoto)
  {
    boolean wasRemoved = false;
    //Unable to remove aPhoto, as it must always have a photoGallery
    if (!this.equals(aPhoto.getPhotoGallery()))
    {
      photos.remove(aPhoto);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPhotoAt(Photo aPhoto, int index)
  {  
    boolean wasAdded = false;
    if(addPhoto(aPhoto))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPhotos()) { index = numberOfPhotos() - 1; }
      photos.remove(aPhoto);
      photos.add(index, aPhoto);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePhotoAt(Photo aPhoto, int index)
  {
    boolean wasAdded = false;
    if(photos.contains(aPhoto))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPhotos()) { index = numberOfPhotos() - 1; }
      photos.remove(aPhoto);
      photos.add(index, aPhoto);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPhotoAt(aPhoto, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Event placeholderEvent = event;
    this.event = null;
    if(placeholderEvent != null)
    {
      placeholderEvent.removePhotoGallery(this);
    }
    for(int i=photos.size(); i > 0; i--)
    {
      Photo aPhoto = photos.get(i - 1);
      aPhoto.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "galleryId" + ":" + getGalleryId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null");
  }
}