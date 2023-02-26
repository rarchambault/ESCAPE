package ca.mcgill.ecse428.ESCAPE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.ESCAPE.dto.PostRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.PostResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EscapeException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Post;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.repository.AttendeeRepository;
import ca.mcgill.ecse428.ESCAPE.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	AttendeeRepository attendeeRepository;

	@Transactional
	public Iterable<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Transactional
	public Post getPostById(int id) throws EscapeException {
		Post post = postRepository.findPostById(id);
		if (post == null) {
			throw new EscapeException(HttpStatus.NOT_FOUND, "Post not found.");
		}
		return post;
	}

	// Add user profile when the repo is made
	@Transactional
	public PostResponseDto createPost(PostRequestDto request) throws EscapeException {
		String content = request.getContent();
		Attendee attendee = attendeeRepository.findAttendeeByUserId(request.getUserId());
		if (attendee == null) {
			throw new EscapeException(HttpStatus.NOT_FOUND, "Post creator not found.");
		}
		Post post = new Post(content, attendee);
		postRepository.save(post);
		return new PostResponseDto(post);
	}

	@Transactional
	public void updatePost(int id, Post post) throws EscapeException {
		Post optionalPost = postRepository.findPostById(id);
		if (optionalPost != null) {
			post.setPostId(id);
			postRepository.save(post);
		} else {
			throw new EscapeException(HttpStatus.NOT_FOUND, "Post not found.");
		}
	}

	@Transactional
	public void deletePost(int id) throws EscapeException {
		Post post = postRepository.findPostById(id);
		if (post == null) {
			throw new EscapeException(HttpStatus.NOT_FOUND, "Post not found.");
		}
		postRepository.delete(post);
	}
}