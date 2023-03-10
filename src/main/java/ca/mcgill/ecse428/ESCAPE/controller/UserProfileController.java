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

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.service.UserProfileService;

@RestController
@RequestMapping("UserProfile")
public class UserProfileController{
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

    @GetMapping()
    public ResponseEntity<Iterable<UserProfileResponseDto>> getAllAttendees() {
        Iterable<Attendee> attendees = userProfileService.getAllAttendees();

        ArrayList<UserProfileResponseDto> attendeeResponses = new ArrayList<UserProfileResponseDto>();

        for (var attendee : attendees) {
            attendeeResponses.add(new UserProfileResponseDto(attendee, "attendee"));
        }

        return new ResponseEntity<Iterable<UserProfileResponseDto>>(attendeeResponses, HttpStatus.OK);
    }
}