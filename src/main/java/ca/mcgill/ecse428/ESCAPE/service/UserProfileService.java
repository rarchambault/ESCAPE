package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;
import java.util.List;

import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.repository.AdminRepository;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {
    private final AttendeeRepository attendeeRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserProfileService(AttendeeRepository attendeeRepository, AdminRepository adminRepository) {
        this.attendeeRepository = attendeeRepository;
        this.adminRepository = adminRepository;
    }

    public UserProfileResponseDto createAdminProfile(UserProfileRequestDto request) {
        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        admin.setName(request.getName());
        adminRepository.save(admin);
        return new UserProfileResponseDto(admin, "admin");
    }

    public UserProfileResponseDto createAttendeeProfile(UserProfileRequestDto request) {
        Attendee attendee = new Attendee();
        attendee.setEmail(request.getEmail());
        attendee.setPassword(request.getPassword());
        attendee.setName(request.getName());
        attendeeRepository.save(attendee);
        return new UserProfileResponseDto(attendee, "attendee");
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
