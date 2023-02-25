package ca.mcgill.ecse428.ESCAPE.dto;


import java.util.List;

public class PhotoRequestDto {
    private int galleryId;
    private int eventId;
    private List<PhotoResponseDto> photos;

    public PhotoRequestDto() {}

    public PhotoRequestDto(int galleryId, int eventId, List<PhotoResponseDto> photos) {
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

    public List<PhotoResponseDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoResponseDto> photos) {
        this.photos = photos;
    }
}

