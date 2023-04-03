package ca.mcgill.ecse428.ESCAPE.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.dto.EventRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class EID001_Create_Event_Step_Def {

    @Autowired
    private EventController eventController;

    private String adminEmail;
    private String adminPassword;
    private String eventName;
    private String eventDescription;
    private Integer eventCapacity;
    private LocalDateTime eventStartTime;
    private ResponseEntity<EventResponseDto> responseEntity;

    @Given("the following admins exist in the system:")
    public void the_following_admins_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // no need to implement as it's already part of the background in the feature file
    }

    @Given("an admin is logged in with email {string} and password {string}")
    public void an_admin_is_logged_in_with_email_and_password(String email, String password) {
        adminEmail = email;
        adminPassword = password;
    }

    @When("the admin attempts to create an event with name {string}, description {string}, capacity {int}, and start time {string}")
    public void the_admin_attempts_to_create_an_event_with_name_description_ticket_price_ID_capacity_and_start_time(String name, String description, Integer capacity, String startTime) {
        eventName = name;
        eventDescription = description;
        eventCapacity = capacity;
        eventStartTime = LocalDateTime.parse(startTime);
        EventRequestDto requestDto = new EventRequestDto();
        requestDto.setName(eventName);
        requestDto.setDescription(eventDescription);
        requestDto.setCapacity(eventCapacity);
        requestDto.setStartTime(eventStartTime);
        responseEntity = eventController.createEvent(requestDto);
    }

    @Then("a new event {string} will be created with description {string}, ticket price {double}, ID {int}, capacity {int}, and start time {string}")
    public void a_new_event_will_be_created_with_description_ticket_price_ID_capacity_and_start_time(String name, String description, Double ticketPrice, Integer id, Integer capacity, String startTime) {
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        EventResponseDto responseDto = responseEntity.getBody();
        assertNotNull(responseDto);
        assertEquals(name, responseDto.getName());
        assertEquals(description, responseDto.getDescription());
        assertEquals(Optional.ofNullable(capacity), responseDto.getCapacity());
        assertEquals(LocalDateTime.parse(startTime), responseDto.getStartTime());
    }

    @Then("the following {string} shall be raised")
    public void the_following_error_shall_be_raised(String expectedError) {
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedError, responseEntity.getBody());
    }
}
