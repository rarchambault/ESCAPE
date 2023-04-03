package ca.mcgill.ecse428.ESCAPE.dto;

public class PostRequestDto {

    // attributes
    private String content;
    private String email;

    public PostRequestDto(String content, String email){
        this.email = email;
        this.content = content;
    }

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
