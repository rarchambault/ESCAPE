package ca.mcgill.ecse428.ESCAPE.service;

import java.util.ArrayList;
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

    public boolean setProfile_picture_path(String email, String profile_picture_path) {
        Attendee attendee = attendeeRepository.findAttendeeByEmail(email);
        Admin admin = adminRepository.findAdminByEmail(email);
        if (attendee != null) {
            attendee.setProfile_picture_path(profile_picture_path);
            attendeeRepository.save(attendee);
            return true;
        } else if (admin != null) {
            admin.setProfile_picture_path(profile_picture_path);
            adminRepository.save(admin);
            return true;
        } else {
            return false;
        }
    }

    public String getProfile_picture_path(String email) {
        Attendee attendee = null;
        Admin admin = null;
        try{
            attendee = attendeeRepository.findAttendeeByEmail(email);
        }
        catch(Exception e){
            System.out.println(e);
        }
        try{
            admin = adminRepository.findAdminByEmail(email);
        }
        catch(Exception e){
            System.out.println(e);
        }
        if (attendee != null) {
            return attendee.getProfile_picture_path();
        } else if (admin != null) {
            return admin.getProfile_picture_path();
        } else {
            return null;
        }
    }

    @Transactional
    public Iterable<Attendee> getAllAttendees() {

        Iterable<Attendee> attendees = attendeeRepository.findAll();

        if (attendees == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendees not found.");
        }

        return attendees;
    }
    @Transactional
    public List<UserProfile> getAllUserProfiles() {

        Iterable<Attendee> attendees = attendeeRepository.findAll();
        Iterable<Admin> admins = adminRepository.findAll();
        List<UserProfile> userProfiles = new ArrayList<UserProfile>();
        if (attendees == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Attendees not found.");
        }
        if (admins == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Admin not found.");
        }
        for (Attendee a : attendees){
            userProfiles.add(a);
        }
        for (Admin a2 : admins){
            userProfiles.add(a2);
        }


        return userProfiles;
    }
    
}
