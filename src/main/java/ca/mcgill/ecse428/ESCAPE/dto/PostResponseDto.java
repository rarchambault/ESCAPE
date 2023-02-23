package ca.mcgill.ecse428.ESCAPE.dto;


import ca.mcgill.ecse428.ESCAPE.model.Post;

public class PostResponseDto {

    private int id;
    private String content;

    public PostResponseDto(Post post) {

        this.id = post.getPostId();
        this.content = post.getContent();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
