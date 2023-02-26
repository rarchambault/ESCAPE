package ca.mcgill.ecse428.ESCAPE.controller;


import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;
import ca.mcgill.ecse428.ESCAPE.dto.PostRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.PostResponseDto;
import ca.mcgill.ecse428.ESCAPE.service.PostService;
import ca.mcgill.ecse428.ESCAPE.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts") // could be "post"
public class PostController {

    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto request) {

          PostResponseDto response = postService.createPost(request);
        return new ResponseEntity<PostResponseDto>(response, HttpStatus.CREATED);

    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable("postId") int postId, Model model) {

        postService.deletePost(postId);

        return "redirect:/posts";
    }


}
