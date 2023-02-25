package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Photo;

import java.util.List;

public class PhotoResponseDto {
    private int photoId;
    private int galleryId;
    private int eventId;


    public PhotoResponseDto(Photo photo) {
        this.photoId = photo.getPhotoId();
        this.galleryId = photo.getPhotoGallery().getGalleryId();
        this.eventId = photo.getPhotoGallery().getEvent().getId();
    }

    public int getPhotoId() { return this.photoId; }

    public void setPhotoId(int photoId) { this.photoId = photoId; }
    public int getGalleryId() {
        return this.galleryId;
    }

    public int getEventId() {
        return this.eventId;
    }

}
