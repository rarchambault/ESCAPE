package ca.mcgill.ecse428.ESCAPE.dto;

public class UserProfileRequestDto {
    
	private String name;
    private String email;
    private String password;
    
    public UserProfileRequestDto(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
    
    public UserProfileRequestDto() {}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
