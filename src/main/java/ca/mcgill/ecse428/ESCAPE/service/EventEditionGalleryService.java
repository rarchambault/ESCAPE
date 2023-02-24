package ca.mcgill.ecse428.ESCAPE.service;

import ca.mcgill.ecse428.ESCAPE.exception.EventException;
import ca.mcgill.ecse428.ESCAPE.model.Photo;
import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;
import ca.mcgill.ecse428.ESCAPE.repository.EventEditionGalleryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventEditionGalleryService {

    @Autowired
    private EventEditionGalleryRepository photoGalleryRepository;

    public Iterable<PhotoGallery> getAllPhotos() {
        return photoGalleryRepository.findAll();
    }

    public PhotoGallery getGalleriesById(int id) throws EventException {
        Optional<PhotoGallery> optionalGallery = photoGalleryRepository.findById(id);
        if (optionalGallery.isPresent()) {
            return optionalGallery.get();
        } else {
            throw new EntityNotFoundException("Gallery not found with id: " + id);
        }
    }

    public List<Photo> getPhotosByGalleryId(int galleryId) {
        Optional<PhotoGallery> optionalPhotoGallery = photoGalleryRepository.findById(galleryId);
        if (optionalPhotoGallery.isPresent()) {
            PhotoGallery photoGallery = optionalPhotoGallery.get();
            List<Photo> photos = photoGallery.getPhotos();
            return photos;

        } else {
            throw new EntityNotFoundException("Photo gallery with id " + galleryId + " not found.");
        }
    }

}
