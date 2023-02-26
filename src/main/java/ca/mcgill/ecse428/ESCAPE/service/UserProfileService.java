package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.repository.AdminRepository;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.exception.UserProfileException;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;

@Service
public class UserProfileService {
    private final AttendeeRepository attendeeRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserProfileService(AttendeeRepository attendeeRepository, AdminRepository adminRepository) {
        this.attendeeRepository = attendeeRepository;
        this.adminRepository = adminRepository;
    }

    public UserProfileResponseDto createAdminProfile(UserProfileDto request) {
        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        admin.setName(request.getName());
        admin.setPhoto(request.getPhoto());
        adminRepository.save(admin);
        return new UserProfileResponseDto(admin, "admin");
    }

    public UserProfileResponseDto createAttendeeProfile(UserProfileDto request) {
        Attendee attendee = new Attendee();
        attendee.setEmail(request.getEmail());
        attendee.setPassword(request.getPassword());
        attendee.setName(request.getName());
        attendee.setPhoto(request.getPhoto());
        attendeeRepository.save(attendee);
        return new UserProfileResponseDto(attendee, "attendee");
    }

    
}
