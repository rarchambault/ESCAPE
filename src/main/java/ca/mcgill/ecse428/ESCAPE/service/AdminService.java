package ca.mcgill.ecse428.ESCAPE.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
    AdminRepository adminRepository;

	@Transactional
    public Iterable<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

	@Transactional
    public Admin getAdminByEmail(String email) throws EscapeException {
        Admin admin = adminRepository.findAdminByEmail(email);
        if (admin == null) {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Admin not found.");
        } 
        return admin;
    }

    // Add user profile when the repo is made
	@Transactional
    public UserProfileResponseDto createAdmin(UserProfileRequestDto request) {
    	// check for invalid inputs
		String name = request.getName();
		String email = request.getEmail();
		String password = request.getPassword();
		if (name == null || email == null || password == null) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Required attributes missing.");
		}
		if (name.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid admin name.");
		}
		if (email.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid admin email.");
		}
		if (password.isBlank()) {
			throw new EscapeException(HttpStatus.BAD_REQUEST, "Invalid admin password.");
		}
    	Admin admin = new Admin(name, email, password);
    	adminRepository.save(admin);
    	return new UserProfileResponseDto(admin, "Admin");
    }

	@Transactional
    public void updateAdmin(String email, Admin admin) throws EscapeException {
        Admin optionalAdmin = adminRepository.findAdminByEmail(email);
        if (optionalAdmin != null) {
            admin.setEmail(email);
            adminRepository.save(admin);
        } else {
            throw new EscapeException(HttpStatus.NOT_FOUND, "Admin not found.");
        }
    }

	@Transactional
    public void deleteAdmin(String email) throws EscapeException {
        Admin admin = adminRepository.findAdminByEmail(email);
        if (admin == null) {
        	throw new EscapeException(HttpStatus.NOT_FOUND, "Admin not found.");
        } 
        adminRepository.delete(admin);
    }
}