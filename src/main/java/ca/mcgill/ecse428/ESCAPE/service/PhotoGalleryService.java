package ca.mcgill.ecse428.ESCAPE.service;

import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;
import ca.mcgill.ecse428.ESCAPE.repository.PhotoGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhotoGalleryService {

    @Autowired
    private PhotoGalleryRepository photoGalleryRepository;

    public Iterable<PhotoGallery> getAllGalleries() {
        return photoGalleryRepository.findAll();
    }

    public PhotoGallery getGalleryById(int id) throws EscapeException {
        Optional<PhotoGallery> optionalPhotoGallery = photoGalleryRepository.findById(id);
        if (optionalPhotoGallery.isPresent()) {
            return optionalPhotoGallery.get();
        } else {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Gallery not found with id: " + id);
        }
    }


}
