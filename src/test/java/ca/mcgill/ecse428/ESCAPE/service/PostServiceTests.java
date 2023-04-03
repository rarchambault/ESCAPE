package ca.mcgill.ecse428.ESCAPE.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse428.ESCAPE.dto.PostRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.PostResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Post;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

	// Replace the repository with a "mock" that exposes the same interface
	@Mock
	PostRepository postRepo;
	@Mock
	AttendeeRepository attendeeRepo;

	// Get a service that uses the mock repository
	@InjectMocks
	PostService postService;
	@InjectMocks
	AttendeeService attendeeService;

	@Test
	public void testGetPostById() {
		// Tell the mocked repository how to behave
		final Post funnyPost = new Post();
		int id = funnyPost.getPostId();
		when(postRepo.findPostById(id)).thenAnswer((InvocationOnMock invocation) -> funnyPost);

		// Test that the service behaves properly
		Post post = postService.getPostById(id);

		assertNotNull(post);
		assertEquals(id, post.getPostId());
	}

	@Test
	public void testGetPostByInvalidId() {
		final int invalidId = 99;

		// Mock: if asking for a post with invalid ID, return null
		when(postRepo.findPostById(invalidId)).thenAnswer((InvocationOnMock invocation) -> null);

		// call method, and obtain resulting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> postService.getPostById(invalidId));

		// check results
		assertEquals("Post not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

	@Test
	public void testCreatePost() {
		// Mock: just return the post with no modification
		when(postRepo.save(any(Post.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));

		// test set up - create a post request
		PostRequestDto postRequest = new PostRequestDto("This was a great post!", "nima.hemati@mail.mcgill.ca");
		String content = "Some content.";
		postRequest.setContent(content);
		
		// test set up - create a person to tie to the post
		Attendee person = new Attendee();
		String email = "finnigan@mail.com";
		String name = "Finn Igan";
		int userId = 1;
		person.setEmail(email);
		person.setName(name);
		// when searching for person using email, return person
		when(attendeeRepo.findAttendeeByEmail(email)).thenAnswer((InvocationOnMock invocation) -> person);
		postRequest.setEmail(email);

		// call method
		PostResponseDto returnedPost = postService.createPost(postRequest);

		// check results
		assertNotNull(returnedPost);
		assertEquals(content, returnedPost.getContent());
		// Check that the service actually saved the post
		// verify(postRepo, times(1)).save();
	}
	
	@Test
	public void testGetAllPosts() {
		
	}

	// test delete post
	@Test
	public void testDeletePost() {
		// setup: create an post to delete
		Attendee person = new Attendee();
		String email = "finnigan@mail.com";
		String name = "Finn Igan";
		person.setEmail(email);
		person.setName(name);
		Post aPost = new Post();
		int id = aPost.getPostId();
		aPost.setAttendee(person);

		// Mock: if looking for post using this posts ID return the post as if it were in the database
		when(postRepo.findPostById(id)).thenAnswer(x -> aPost);

		// call method
		postService.deletePost(id);

		// check results
		verify(postRepo, times(1)).delete(aPost);
	}

	// test invalid delete post - not found
	@Test
	public void testInvalidDeletePost() {
		// Mock: if searching using an invalid post id return null
		when(postRepo.findPostById(any(int.class))).thenAnswer(x -> null);

		// call method, expecting exception
		EscapeException ex = assertThrows(EscapeException.class,
				() -> postService.deletePost(Integer.MAX_VALUE));

		// check results
		assertEquals(ex.getMessage(), "Post not found.");
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}

}
