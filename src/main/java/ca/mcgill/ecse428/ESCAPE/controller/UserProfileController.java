package ca.mcgill.ecse428.ESCAPE.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.service.UserProfileService;

import ca.mcgill.ecse428.ESCAPE.storage.*;

@RestController
@RequestMapping("UserProfile")
public class UserProfileController{

    private final StorageService storageService;

    @Autowired
    public UserProfileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/admin")
    public ResponseEntity<UserProfileResponseDto> createAdminProfile(@RequestBody UserProfileRequestDto request) {
        UserProfileResponseDto response = userProfileService.createAdminProfile(request);
        return new ResponseEntity<UserProfileResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping("/attendee")
    public ResponseEntity<UserProfileResponseDto> createAttendeeProfile(@RequestBody UserProfileRequestDto request) {
        UserProfileResponseDto response = userProfileService.createAttendeeProfile(request);
        return new ResponseEntity<UserProfileResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping("/profilePicture/{email}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable String email, @RequestParam("file") MultipartFile file) {
        storageService.store(file);
        if(userProfileService.setProfile_picture_path(email, file.getOriginalFilename())){
            return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                    .body(String.format("Successfully uploaded %s!", file.getOriginalFilename()));
        }
        return ResponseEntity.badRequest().body("Profile picture upload failed");
    }

    @GetMapping("/profilePicture/{email}")
    public ResponseEntity<Resource> serveFile(@PathVariable String email) {
        String filename = userProfileService.getProfile_picture_path(email);
        if(filename == "" || filename == null) {
            return ResponseEntity.noContent().build();
        }
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/attendees")
    public ResponseEntity<Iterable<UserProfileResponseDto>> getAllAttendees() {
        Iterable<Attendee> attendees = userProfileService.getAllAttendees();

        ArrayList<UserProfileResponseDto> attendeeResponses = new ArrayList<UserProfileResponseDto>();

        for (var attendee : attendees) {
            attendeeResponses.add(new UserProfileResponseDto(attendee, "attendee"));
        }

        return new ResponseEntity<Iterable<UserProfileResponseDto>>(attendeeResponses, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<UserProfileResponseDto>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();

        ArrayList<UserProfileResponseDto> userProfileResponses = new ArrayList<UserProfileResponseDto>();

        for (var userProfile : userProfiles) {
            userProfileResponses.add(new UserProfileResponseDto(userProfile, "userProfile"));
        }

        return new ResponseEntity<Iterable<UserProfileResponseDto>>(userProfileResponses, HttpStatus.OK);
    }
}