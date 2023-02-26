package ca.mcgill.ecse428.ESCAPE.dto;

public class PhotoGalleryRequestDto {
    private int galleryId;
    private int eventId;

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
