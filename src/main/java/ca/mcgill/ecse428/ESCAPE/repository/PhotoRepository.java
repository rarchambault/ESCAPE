package ca.mcgill.ecse428.ESCAPE.repository;

import ca.mcgill.ecse428.ESCAPE.model.Photo;

import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    Photo findPhotoByPhotoId(int photoId);
}
