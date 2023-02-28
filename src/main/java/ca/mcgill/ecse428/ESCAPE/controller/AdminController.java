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
import ca.mcgill.ecse428.ESCAPE.model.Admin;
import ca.mcgill.ecse428.ESCAPE.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    // create admin
 	@PostMapping("/admin")
 	public ResponseEntity<UserProfileResponseDto> createAdmin(@RequestBody UserProfileRequestDto request) {
 		UserProfileResponseDto response = adminService.createAdmin(request);
 		return new ResponseEntity<UserProfileResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @DeleteMapping("/admin/{email}")
    public void deleteAdmin(@PathVariable String email) {
        adminService.deleteAdmin(email);
    }

    @GetMapping(value = "/admin/{email}")
	public ResponseEntity<UserProfileResponseDto> getAdminById(@PathVariable String email) {
		Admin admin = adminService.getAdminByEmail(email);
		return new ResponseEntity<UserProfileResponseDto>(new UserProfileResponseDto(admin, "Admin"),
				HttpStatus.OK);
	}


}