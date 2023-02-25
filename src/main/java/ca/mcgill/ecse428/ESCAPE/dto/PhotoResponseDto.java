package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Photo;

import java.util.List;

public class PhotoResponseDto {
    private int galleryId;
    private int eventId;
    private List<PhotoResponseDto> photos;

    public PhotoResponseDto(Photo photos) {
        this.galleryId = photos.getPhotoGallery().getGalleryId();
        this.eventId = photos.getPhotoGallery().getEvent().getId();
    }

    public int getGalleryId() {
        return this.galleryId;
    }

    public int getEventId() {
        return this.eventId;
    }

    public List<PhotoResponseDto> getPhotos() {
        return this.photos;
    }
}
