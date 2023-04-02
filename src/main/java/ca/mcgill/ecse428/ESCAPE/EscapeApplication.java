package ca.mcgill.ecse428.ESCAPE;

import ca.mcgill.ecse428.ESCAPE.controller.AdminController;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.UserProfileResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

@RestController
@SpringBootApplication
public class EscapeApplication {

	@Autowired
	AdminController adminController;

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		try{
			// Check if the admin was created
			adminController.getAdminById("admin@esc.com");
		}
		catch (EscapeException e){
			UserProfileRequestDto adminRequest = new UserProfileRequestDto("admin", "admin@esc.com", "password");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(EscapeApplication.class, args);
	}

}
