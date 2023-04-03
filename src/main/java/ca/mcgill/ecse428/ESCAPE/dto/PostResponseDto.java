package ca.mcgill.ecse428.ESCAPE.dto;


import ca.mcgill.ecse428.ESCAPE.model.Post;
import ca.mcgill.ecse428.ESCAPE.model.Reply;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDto {

    private int id;

    private String email;

    private String name;
    private String content;

    private LocalDateTime date;

    private List<Reply> replies;

    public PostResponseDto(Post post) {

        this.id = post.getPostId();
        this.content = post.getContent();
        this.email = post.getAttendee().getEmail();
        this.name = post.getAttendee().getName();
        this.replies = post.getReplies();
        this.date = post.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getContent() {
		return content;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public boolean setDate(LocalDateTime aDate) {
        boolean wasSet = false;
        date = aDate;
        wasSet = true;
        return wasSet;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
