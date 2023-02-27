package ca.mcgill.ecse428.ESCAPE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.service.AttendeeService;

@CrossOrigin(origins = "*")
@RestController
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    // create attendee
 	@PostMapping("/attendee")
 	public ResponseEntity<UserProfileResponseDto> createAttendee(@RequestBody UserProfileRequestDto request) {
 		UserProfileResponseDto response = attendeeService.createAttendee(request);
 		return new ResponseEntity<UserProfileResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @DeleteMapping("/attendee/{id}")
    public String deleteAttendee(@PathVariable String email) {

        attendeeService.deleteAttendee(email);
        return "redirect:/attendee";
    }

    @GetMapping(value = "/attendee/{email}")
	public ResponseEntity<UserProfileResponseDto> getAttendeeById(@PathVariable String email) {
		Attendee attendee = attendeeService.getAttendeeByEmail(email);
		return new ResponseEntity<UserProfileResponseDto>(new UserProfileResponseDto(attendee, "Attendee"),
				HttpStatus.OK);
	}


}