package ca.mcgill.ecse428.ESCAPE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.ESCAPE.model.Reply;
import ca.mcgill.ecse428.ESCAPE.repository.ReplyRepository;


@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public Reply getReplyById(int id) {
        Optional<Reply> optionalReply = replyRepository.findById(id);
        if (optionalReply.isPresent()) {
            return optionalReply.get();
        }
        return null;
    }

    public Reply addReply(Reply reply) {
        return replyRepository.save(reply);
    }

    // Uncomment and change as needed when PostRepository is implemented 

    // @Autowired
    // private PostRepository postRepository;

    // public Reply addReply(Reply reply, int postId) {
    //     Post post = postRepository.findById(postId).orElse(null);
    //     if (post == null) {
    //         return null;
    //     }
    //     reply.setPost(post);
    //     return replyRepository.save(reply);
    // }

    public List<Reply> getAllReplies() {
        return (List<Reply>) replyRepository.findAll();
    }

    public Reply updateReply(int id, Reply updatedReply) {
        Optional<Reply> optionalReply = replyRepository.findById(id);
        if (optionalReply.isPresent()) {
            Reply existingReply = optionalReply.get();
            existingReply.setContent(updatedReply.getContent());
            existingReply.setPost(updatedReply.getPost());
            return replyRepository.save(existingReply);
        }
        return null;
    }

    public boolean deleteReply(int id) {
        Optional<Reply> optionalReply = replyRepository.findById(id);
        if (optionalReply.isPresent()) {
            Reply replyToDelete = optionalReply.get();
            replyRepository.delete(replyToDelete);
            return true;
        }
        return false;
    }

}