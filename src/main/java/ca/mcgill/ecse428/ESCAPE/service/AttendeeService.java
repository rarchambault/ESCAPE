package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;

@Service
public class AttendeeService {

	@Autowired
    AttendeeRepository attendeeRepository;

	@Transactional
    public Iterable<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

	@Transactional
    public Attendee getAttendeeById(String email) throws EscapeException {
        Attendee attendee = attendeeRepository.findAttendeeByEmail(email);
        if (attendee == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
        } 
        return attendee;
    }

    // Add user profile when the repo is made
	@Transactional
    public UserProfileResponseDto createAttendee(UserProfileRequestDto request) {
    	UserProfile creator = null;
    	// check if the name is blank
		String name = request.getName();
		String email = request.getEmail();
		String password = request.getPassword();
		if (name.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid attendee name.");
		}
		if (email.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid attendee email.");
		}
		if (password.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid attendee password.");
		}
    	Attendee attendee = new Attendee(name, email, password);
    	attendeeRepository.save(attendee);
    	return new UserProfileResponseDto(attendee, "Attendee");
    }

	@Transactional
    public void updateAttendee(String email, Attendee attendee) throws EscapeException {
        Attendee optionalAttendee = attendeeRepository.findAttendeeByEmail(email);
        if (optionalAttendee != null) {
            attendee.setEmail(email);
            attendeeRepository.save(attendee);
        } else {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
        }
    }

	@Transactional
    public void deleteAttendee(String email) throws EscapeException {
        Attendee attendee = attendeeRepository.findAttendeeByEmail(email);
        if (attendee == null) {
        	throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
        } 
        attendeeRepository.delete(attendee);
    }
}