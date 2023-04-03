package ca.mcgill.ecse428.ESCAPE;

import ca.mcgill.ecse428.ESCAPE.controller.*;
import ca.mcgill.ecse428.ESCAPE.dto.*;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.ESCAPE.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ca.mcgill.ecse428.ESCAPE.storage.StorageProperties;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

@RestController
@SpringBootApplication
@EnableConfigurationProperties({
	StorageProperties.class
})
public class EscapeApplication {

	@Autowired
	AdminController adminController;

	@Autowired
	AttendeeController attendeeController;

	@Autowired
	EventController eventController;

	@Autowired
	TicketController ticketController;

	@Autowired
	PostController postController;

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		try{
			// Check if the admin was created
			adminController.getAdminById("admin@esc.com");
		}
		catch (EscapeException e){
			UserProfileRequestDto adminRequest = new UserProfileRequestDto("admin", "admin@esc.com", "password");
			adminController.createAdmin(adminRequest);

			EventRequestDto eventRequest = new EventRequestDto(
					"Spring Power Hour",
					"Come drink 60 sips in 60 minutes with this special spring themed event!",
					200,
					LocalDateTime.of(LocalDate.of(2023, 4, 7), LocalTime.of(19, 0)),
					"Cafe Campus");

			ResponseEntity<EventResponseDto> eventResponse = eventController.createEvent(eventRequest);

			TicketRequestDto ticketRequest = new TicketRequestDto(
					"Spring Power Hour General Admission",
					10,
					eventResponse.getBody().getId());

			ticketController.createTicket(ticketRequest);

			EventRequestDto eventRequest2 = new EventRequestDto(
					"Easter Egg Hunt",
					"Find all eggs hidden to get a surprise!",
					20,
					LocalDateTime.of(LocalDate.of(2023, 4, 7), LocalTime.of(7, 0)),
					"McConnell Engineering Building 204");

			ResponseEntity<EventResponseDto> eventResponse2 = eventController.createEvent(eventRequest2);

			TicketRequestDto ticketRequest2 = new TicketRequestDto(
					"Easter Egg Hunt General Entry",
					5,
					eventResponse2.getBody().getId());

			ticketController.createTicket(ticketRequest2);

			EventRequestDto eventRequest3 = new EventRequestDto(
					"Flower Market",
					"Buy flowers to celebrate the arrival of spring!",
					50,
					LocalDateTime.of(LocalDate.of(2023, 4, 6), LocalTime.of(12, 0)),
					"Trottier 1100");

			ResponseEntity<EventResponseDto> eventResponse3 = eventController.createEvent(eventRequest3);

			TicketRequestDto ticketRequest3 = new TicketRequestDto(
					"Flower Market General Entry",
					5,
					eventResponse3.getBody().getId());

			ticketController.createTicket(ticketRequest3);

			UserProfileRequestDto userRequest = new UserProfileRequestDto("Nima Hemati", "nima.hemati@mail.mcgill.ca", "password");
			attendeeController.createAttendeeProfile(userRequest);

			PostRequestDto postRequest = new PostRequestDto(
					"This was a great event!",
					"nima.hemati@mail.mcgill.ca"
					);

			//postController.createPost(postRequest);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(EscapeApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
