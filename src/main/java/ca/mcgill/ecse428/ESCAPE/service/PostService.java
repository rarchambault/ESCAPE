package ca.mcgill.ecse428.ESCAPE.service;


import ca.mcgill.ecse428.ESCAPE.dto.PostRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.PostResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.EventException;
import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.model.Post;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import ca.mcgill.ecse428.ESCAPE.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository; // make interfdace


    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Iterable<Post> getAllPost() {
        return postRepository.findAll();
    }

    public PostResponseDto createEvent(PostRequestDto request) {
        Attendee creator = null;
        Post post = new Post(request.getContent(),0,creator);
        postRepository.save(post);
        return new PostResponseDto(post);

    }

    public void deletePost(int id) throws EventException {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            postRepository.delete(optionalPost.get());
        } else {
            throw new EventException(HttpStatus.NOT_FOUND, "Event not found with id: " + id);

        }
    }
}

