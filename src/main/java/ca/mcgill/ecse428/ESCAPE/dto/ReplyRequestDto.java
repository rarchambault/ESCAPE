package ca.mcgill.ecse428.ESCAPE.dto;

public class ReplyRequestDto {
    private String content;
    private Integer postId;

    public ReplyRequestDto() {}

    public ReplyRequestDto(String content, Integer postId) {
        this.content = content;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}