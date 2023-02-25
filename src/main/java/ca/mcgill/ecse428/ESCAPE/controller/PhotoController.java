package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.dto.PhotoResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Photo;
import ca.mcgill.ecse428.ESCAPE.service.PhotoService;
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
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping
    public ResponseEntity<Iterable<PhotoResponseDto>> getAllPhotos(){
        Iterable<Photo> photos = photoService.getAllPhotos();
        ArrayList<PhotoResponseDto> photoResponses = new ArrayList<PhotoResponseDto>();
        for (var photo : photos) {
            photoResponses.add(new PhotoResponseDto(photo));
        }

        return new ResponseEntity<Iterable<PhotoResponseDto>>(photoResponses, HttpStatus.OK);
    }


}
