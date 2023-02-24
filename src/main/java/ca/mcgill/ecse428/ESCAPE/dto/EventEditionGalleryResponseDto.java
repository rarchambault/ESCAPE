package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Photo;
import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;

import java.util.List;

public class EventEditionGalleryResponseDto {
    private int galleryId;
    private int eventId;
    private List<PhotoDto> photos;

    public EventEditionGalleryResponseDto(PhotoGallery photos) {
        this.galleryId = photos.getGalleryId();
        this.eventId = photos.getEvent().getEventId();
    }

    public int getGalleryId() {
        return this.galleryId;
    }

    public int getEventId() {
        return this.eventId;
    }

    public List<PhotoDto> getPhotos() {
        return this.photos;
    }
}
