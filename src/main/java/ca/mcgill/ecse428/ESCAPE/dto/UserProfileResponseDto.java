package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.UserProfile;

public class UserProfileResponseDto {
    
    public String email;
    public String name;
    public int userId;
    public String role;

    public UserProfileResponseDto( String name, String email,int userId, String role) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.role = role;
    }

    public UserProfileResponseDto(UserProfile userProfile, String role) {
        this.email = userProfile.getEmail();
        this.name = userProfile.getName();
        this.role = role;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
