package ca.mcgill.ecse428.ESCAPE.service;

import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Photo;
import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;
import ca.mcgill.ecse428.ESCAPE.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Iterable<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(int id) throws EscapeException {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        if (optionalPhoto.isPresent()) {
            return optionalPhoto.get();
        } else {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Photo not found with id: " + id);
        }
    }

    public List<Photo> getPhotosByGalleryId(int galleryId) {
        Optional<Photo> optionalPhoto = photoRepository.findById(galleryId);
        if (optionalPhoto.isPresent()) {
            PhotoGallery photoGallery = optionalPhoto.get().getPhotoGallery();
            List<Photo> photos = photoGallery.getPhotos();
            return photos;

        } else {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Photo gallery with id " + galleryId + " not found.");
        }
    }

}
