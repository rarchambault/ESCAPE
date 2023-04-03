package ca.mcgill.ecse428.ESCAPE.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import ca.mcgill.ecse428.ESCAPE.controller.PostController;
import ca.mcgill.ecse428.ESCAPE.dto.PostResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Post;
import ca.mcgill.ecse428.ESCAPE.model.Reply;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.repository.PostRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // set random port
public class PostIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private AttendeeRepository attendeeRepo;

	@BeforeEach
	public void clearDatabase() {
		postRepo.deleteAll();
		attendeeRepo.deleteAll();
	}

	@Test
	public void testCreateAndGetAndDeletePost() {
		int id = testCreatePost();
		testGetPost(id);
		testDeletePost(id);
	}

	private int testCreatePost() {
		
		// setup - first create and save person that will create the post
		Attendee person = new Attendee();
		String email = "obi@kenobi.com";
		person.setEmail(email);
		attendeeRepo.save(person);
		
		// set up post
		PostDto dto = new PostDto();
        String content = "Some post content.";
		dto.setContent(content);
		dto.setEmail(email);

		// call method: create a new post
		ResponseEntity<PostDto> response = client.postForEntity("/post", dto, PostDto.class);

		// check response
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(content, response.getBody().content, "Response has correct content");
		assertTrue(response.getBody().id > 0, "Response has valid ID");

		return response.getBody().id;
	}

	private void testGetPost(int id) {

		// call method: get the post by their id
		ResponseEntity<PostDto> response = client.getForEntity("/post/" + id, PostDto.class);

		// check response
		assertNotNull(response);
		System.out.println(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertTrue(response.getBody().id == id, "Response has correct ID");
	}

	@Test
	public void testGetInvalidPost() {
		ResponseEntity<String> response = client.getForEntity("/post/" + Integer.MAX_VALUE, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
		assertEquals("Post not found.", response.getBody(), "Response has correct error message");
	}

	// test delete post
	private void testDeletePost(int id) {
		client.delete("/post/" + id);
		try {
			client.getForEntity("/post/" + id, PostResponseDto.class);
			fail("Post was found!");
		} catch (RestClientException | IllegalArgumentException e) {
		}
	}
}

class PostDto {
	public int id;
	public String content;
	public String email;

	// Need default constructor so that Jackson can instantiate the object
	public PostDto() {
	}

	public void setContent(String content) {
		this.content = content;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Test
	public void testSetPostText() {
//    	String newText = "New post text";
//    	Attendee attendee = new Attendee();
//    	Post post = new Post("Old post text", attendee);
//    	PostController controller = new PostController();
//
//    	controller.setPostText(id, newText);
//    	assertEquals(newText, post.getTextBelow());
	}

	@Test
	public void testAddReply() {
//    	Reply reply1 = new Reply("Comment 1", id, null);
//    	Reply reply2 = new Reply("Comment 2", id, null);
//    	Attendee attendee = new Attendee();
//		Post post = new Post("Post text", attendee);
//    	post.addReply(reply1);
//    	post.addReply(reply2);
//
//    	assertEquals(2, post.getReplies().size());
//    	assertTrue(post.getReplies().contains(reply1));
//    	assertTrue(post.getReplies().contains(reply2));
	}

}
