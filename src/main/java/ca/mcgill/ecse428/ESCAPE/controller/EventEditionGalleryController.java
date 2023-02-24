package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.dto.EventEditionGalleryResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Photo;
import ca.mcgill.ecse428.ESCAPE.model.PhotoGallery;
import ca.mcgill.ecse428.ESCAPE.service.EventEditionGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/photos")
public class EventEditionGalleryController {

    @Autowired
    EventEditionGalleryService eventEditionGalleryService;

    @GetMapping()
    public ResponseEntity<Iterable<EventEditionGalleryResponseDto>> getAllPhotos(){
        Iterable<PhotoGallery> photos = eventEditionGalleryService.getAllPhotos();
        ArrayList<EventEditionGalleryResponseDto> eventEditionGalleryResponses = new ArrayList<EventEditionGalleryResponseDto>();
        for (var photo : photos) {
            eventEditionGalleryResponses.add(new EventEditionGalleryResponseDto(photo));
        }

        return new ResponseEntity<Iterable<EventEditionGalleryResponseDto>>(eventEditionGalleryResponses, HttpStatus.OK);
    }


}
