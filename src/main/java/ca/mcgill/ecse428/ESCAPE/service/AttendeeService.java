package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

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
    public Iterable<Ticket> getAllTicketsForAttendee(String attendeeEmail) {
        Attendee attendee = getAttendeeByEmail(attendeeEmail);
        return attendee.getTickets();
    }

	@Transactional
    public Attendee getAttendeeByEmail(String email) throws EscapeException {
        Attendee attendee = attendeeRepository.findAttendeeByEmail(email);
        if (attendee == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendee not found.");
        } 
        return attendee;
    }

    // Add user profile when the repo is made
	@Transactional
    public UserProfileResponseDto createAttendee(UserProfileRequestDto request) throws EscapeException{
    	// check for invalid inputs
		String name = "";
		String email = "";
		String password = "";
		try {
			name = request.getName();
			email = request.getEmail();
			password = request.getPassword();
		} catch(RestClientException e) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Required attributes missing.");
		}
		if (name.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid attendee name.");
		}
		if (email.isEmpty() || email.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid attendee email.");
		}
		if (password.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid attendee password.");
		}
    	Attendee attendee = new Attendee(name, email, password);
    	attendeeRepository.save(attendee);
    	return new UserProfileResponseDto(name, email, 0, "Attendee");
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