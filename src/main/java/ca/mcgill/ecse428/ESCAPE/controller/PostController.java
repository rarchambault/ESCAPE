package ca.mcgill.ecse428.ESCAPE.controller;

import ca.mcgill.ecse428.ESCAPE.model.Reply;
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

import ca.mcgill.ecse428.ESCAPE.dto.PostRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.PostResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Post;
import ca.mcgill.ecse428.ESCAPE.service.PostService;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    // create post
 	@PostMapping("/post")
 	public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto request) {
 		PostResponseDto response = postService.createPost(request);
 		return new ResponseEntity<PostResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable int id) {

        postService.deletePost(id);
    }

    @GetMapping(value = "/post/{id}")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable int id) {
		Post post = postService.getPostById(id);
		return new ResponseEntity<PostResponseDto>(new PostResponseDto(post),
				HttpStatus.OK);
	}

	@GetMapping("/post")
	public ResponseEntity<Iterable<PostResponseDto>> getAllPosts() {
		Iterable<Post> posts = postService.getAllPosts();

		ArrayList<PostResponseDto> postResponses = new ArrayList<PostResponseDto>();

		for (var post : posts) {
			postResponses.add(new PostResponseDto(post));
		}

		return new ResponseEntity<Iterable<PostResponseDto>>(postResponses, HttpStatus.OK);
	}

	@PostMapping("/post/{id}/text")
	public ResponseEntity<?> setPostText(@PathVariable int id, @RequestBody String text) {
    	Post post = postService.getPostById(id);
    	if (post == null) {
    	    return new ResponseEntity<>("Post not found with id " + id, HttpStatus.NOT_FOUND);
    	}
    	postService.setTextBelow(id, text);
    	return ResponseEntity.ok().build();
	}

	@PostMapping("/post/{id}/comment")
	public ResponseEntity<?> addReply(@PathVariable int id, @RequestBody Reply reply) {
    	Post post = postService.getPostById(id);
    	if (post == null) {
    	    return new ResponseEntity<>("Post not found with id " + id, HttpStatus.NOT_FOUND);
    	}
    	post.addReply(reply.getContent(), id, reply.getAttendee());
    	return ResponseEntity.ok().build();
	}
}