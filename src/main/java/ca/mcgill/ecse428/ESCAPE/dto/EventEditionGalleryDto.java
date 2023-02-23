package ca.mcgill.ecse428.ESCAPE.dto;


import java.util.List;

public class EventEditionGalleryDto {
    private int galleryId;
    private int eventId;
    private List<PhotoDto> photos;

    public EventEditionGalleryDto() {}

    public EventEditionGalleryDto(int galleryId, int eventId, List<PhotoDto> photos) {
        this.galleryId = galleryId;
        this.eventId = eventId;
        this.photos = photos;
    }

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

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }
}

