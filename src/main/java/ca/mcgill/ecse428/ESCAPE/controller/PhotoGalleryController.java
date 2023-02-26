package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.dto.PhotoGalleryResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;
import ca.mcgill.ecse428.ESCAPE.service.PhotoGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gallery")
public class PhotoGalleryController {

    @Autowired
    PhotoGalleryService photoGalleryService;

    @GetMapping
    public ResponseEntity<Iterable<PhotoGalleryResponseDto>> getAllGalleries(){
        Iterable<PhotoGallery> galleries = photoGalleryService.getAllGalleries();
        ArrayList<PhotoGalleryResponseDto> photoGalleryResponses = new ArrayList<PhotoGalleryResponseDto>();
        for (var gallery : galleries) {
            photoGalleryResponses.add(new PhotoGalleryResponseDto(gallery));
        }

        return new ResponseEntity<Iterable<PhotoGalleryResponseDto>>(photoGalleryResponses, HttpStatus.OK);
    }

}
