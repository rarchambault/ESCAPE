package ca.mcgill.ecse428.ESCAPE.service;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.repository.AdminRepository;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AttendeeService {

  @Autowired
  AttendeeRepository attendeeRepository;

  // creating user profile
  @Transactional
  public Attendee createAttendee(String name, String email, String password, String photo)
  {
    Attendee currentAttendee = attendeeRepository.findAttendeeByEmail(email);
    return null;
  }

  @Transactional
  public Iterable<Attendee> getAllAttendees() {
    Iterable<Attendee> attendees = attendeeRepository.findAll();

    if (attendees == null) {
      throw new EscapeException(HttpStatus.NOT_FOUND, "Attendees not found.");
    }

    return attendees;
  }
}