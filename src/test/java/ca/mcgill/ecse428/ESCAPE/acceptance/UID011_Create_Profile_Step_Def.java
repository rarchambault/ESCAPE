//package ca.mcgill.ecse428.ESCAPE.acceptance;
//
//import static org.junit.Assert.assertEquals;
//        import static org.junit.Assert.assertNotNull;
//
//        import java.util.List;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.http.ResponseEntity;
//
//        import io.cucumber.java.en.Given;
//        import io.cucumber.java.en.Then;
//        import io.cucumber.java.en.When;
//
//public class UID011_Create_Profile_Step_Def {
//
//    @Autowired
//    private UserProfileService userProfileService;
//
//    private List<UserProfileDto> existingAttendees;
//    private UserProfileDto newAttendee;
//    private ResponseEntity<UserProfileResponseDto> response;
//    private String error;
//
//    @Given("the following attendees exist in the system:")
//    public void the_following_attendees_exist_in_the_system(List<UserProfileDto> attendees) {
//        existingAttendees = attendees;
//        // Add existing attendees to the system using userProfileService
//        for (UserProfileDto attendee : existingAttendees) {
//            userProfileService.createAdminProfile(attendee);
//        }
//    }
//
//    @When("a new attendee attempts to register with {string}, {string}, {string}, {string}, and {int}")
//    public void a_new_attendee_attempts_to_register_with_and(String name, String email, String password, String photo, int userId) {
//        // Create a new UserProfileDto object with the given attributes
//        newAttendee = new UserProfileDto();
//        newAttendee.setName(name);
//        newAttendee.setEmail(email);
//        newAttendee.setPassword(password);
//        newAttendee.setPhoto(photo);
//        newAttendee.setUserId(userId);
//
//        // Call the createAdminProfile method of the userProfileService with the new attendee
//        response = userProfileService.createAdminProfile(newAttendee);
//    }
//
//    @Then("a new attendee profile shall exist with {string}, {string}, {string}, {string}, and {int}")
//    public void a_new_attendee_profile_shall_exist_with_and(String name, String email, String password, String photo, int userId) {
//        // Verify that the response entity has the correct status code
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        // Verify that the response body is not null
//        UserProfileResponseDto responseBody = response.getBody();
//        assertNotNull(responseBody);
//
//        // Verify that the response body has the correct values
//        assertEquals(email, responseBody.email);
//        assertEquals(name, responseBody.name);
//        assertEquals(photo, responseBody.photo);
//        assertEquals(userId, responseBody.userId);
//        assertEquals("admin", responseBody.role);
//    }
//
//    @Then("there are {int} attendees in the system")
//    public void there_are_attendees_in_the_system(int numAttendees) {
//        // Verify that the number of attendees in the system is correct
//        List<UserProfileDto> attendees = userProfileService.getAllAttendees();
//        assertEquals(numAttendees, attendees.size());
//    }
//
//    @Then("the following {string} shall be raised")
//    public void the_following_error_shall_be_raised(String expectedError) {
//        // Verify that the response entity has the correct status code
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        // Verify that the response body is null
//        UserProfileResponseDto responseBody = response.getBody();
//        assertEquals(null, responseBody);
//
//        // Verify that the correct error message is returned
//        assertEquals(expectedError, error);
//    }
//
//    @Then("there is no member account for {string}")
//    public void there_is_no_member_account_for(String email) {
//        // Verify that no attendee with the specified email exists in the
