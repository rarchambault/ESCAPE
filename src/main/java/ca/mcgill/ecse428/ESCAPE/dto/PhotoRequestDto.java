package ca.mcgill.ecse428.ESCAPE.dto;


import java.util.List;

public class PhotoRequestDto {
    private int photoId;
    private int galleryId;
    private int eventId;

    public int getPhotoId() { return photoId; }

    public void setPhotoId(int photoId) { this.photoId = photoId; }
    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}

