package ca.mcgill.ecse428.ESCAPE.repository;

import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;
import org.springframework.data.repository.CrudRepository;

public interface EventEditionGalleryRepository extends CrudRepository<PhotoGallery, Integer> {
    PhotoGallery findEventEditionGalleryById(int id);
}
