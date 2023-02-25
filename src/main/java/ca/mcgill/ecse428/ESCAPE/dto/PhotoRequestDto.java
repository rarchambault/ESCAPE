package ca.mcgill.ecse428.ESCAPE.dto;


import java.util.List;

public class PhotoRequestDto {
    private int photoId;
    private String photoPath;
    private int galleryId;
    private int eventId;

    public int getPhotoId() { return photoId; }

    public void setPhotoId(int photoId) { this.photoId = photoId; }

    public String getPhotoPath() { return photoPath; }

    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
    public int getGalleryId() { return galleryId; }

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

