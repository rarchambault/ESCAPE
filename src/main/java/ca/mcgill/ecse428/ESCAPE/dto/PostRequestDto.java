package ca.mcgill.ecse428.ESCAPE.dto;

public class PostRequestDto {

    // attributes
    private String content;
    private String email;

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    private String attendeeEmail;

    public String getAttendeeEmail() {
        return attendeeEmail;
    }
    public void setAttendeeEmail(String attendeeEmail) {
        this.attendeeEmail = attendeeEmail;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
