package ca.mcgill.ecse428.ESCAPE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Post;

@SpringBootTest
public class PostRepositoryTests {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private AttendeeRepository attendeeRepository;

	@BeforeEach
	public void prepareDatabase() {
		postRepository.deleteAll();
		attendeeRepository.deleteAll();
	}

	@AfterEach
	public void clearDatabase() {
		postRepository.deleteAll();
		attendeeRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadPost() {

		// Create attendee object
		String name = "Sandy Sand";
		String email = "andy@hotmail.com";
		String password = "pass";
		Attendee sandy = new Attendee(name, email, password);
		sandy = attendeeRepository.save(sandy);

		// Create object
		Post aPost = new Post();
		aPost.setAttendee(sandy);

		// Save object
		aPost = postRepository.save(aPost);
		int postId = aPost.getPostId();

		// Read object from database
		aPost = null;
		aPost = postRepository.findPostById(postId);

		// Assert that object has correct attributes
		assertNotNull(aPost);
		assertEquals(postId, aPost.getPostId());
	}

	@Test
	public void testPostToAttendeeReference() {

		// Create attendee object
		String email = "sandy@hotmail.com";
		Attendee sandy = new Attendee();
		sandy.setEmail(email);
		sandy = attendeeRepository.save(sandy);

		// Create Post
		Post aPost = new Post();
		aPost.setAttendee(sandy);

		// Update object
		aPost = postRepository.save(aPost);
		int postID = aPost.getPostId();

		// Read object from database
		aPost = postRepository.findPostById(postID);

		// Assert that object has correct attributes
		assertNotNull(aPost);
		assertEquals(postID, aPost.getPostId());
		assertEquals(email, aPost.getAttendee().getEmail());
	}

}