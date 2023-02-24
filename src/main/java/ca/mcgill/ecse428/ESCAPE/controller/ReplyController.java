package ca.mcgill.ecse428.ESCAPE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.ESCAPE.model.Reply;
import ca.mcgill.ecse428.ESCAPE.model.Post;

import ca.mcgill.ecse428.ESCAPE.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReply(@PathVariable int id) {
        Reply reply = replyService.getReplyById(id);
        if (reply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Reply> addReply(@RequestBody Reply reply) {

    // Uncomment when post stuff has been implemented 
    // public ResponseEntity<Reply> addReply(@RequestBody Reply reply, @RequestParam("postId") int postId) {

        // Uncomment when post stuff has been implemented
        // Reply newReply = replyService.addReply(reply, postId);

        Reply newReply = replyService.addReply(reply);

        if (newReply == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newReply, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Reply>> getAllReplies() {
        List<Reply> replies = replyService.getAllReplies();
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reply> updateReply(@PathVariable int id, @RequestBody Reply reply) {
        Reply updatedReply = replyService.updateReply(id, reply);
        if (updatedReply == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReply, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReply(@PathVariable int id) {
        boolean success = replyService.deleteReply(id);
        if (!success) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}