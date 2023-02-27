package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;

public class PhotoGalleryResponseDto {
    private int galleryId;
    private int eventId;

    public PhotoGalleryResponseDto(PhotoGallery gallery) {
        this.galleryId = gallery.getGalleryId();
        this.eventId = gallery.getEvent().getId();
    }

    public int getGalleryId() {
        return this.galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public int getEventId() {
        return this.eventId;
    }
}
