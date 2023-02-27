package ca.mcgill.ecse428.ESCAPE.dto;

public class PostRequestDto {

    // attributes
    private String content;
    private int userId;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


}
