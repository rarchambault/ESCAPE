package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.util.*;

// line 2 "model.ump"
// line 77 "model.ump"
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserProfile {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// UserProfile Attributes
	private String name;
	@Id
	private String email;
	private String password;
	private String photo;
	private int userId;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public UserProfile(String aName, String aEmail, String aPassword, String aPhoto) {
		name = aName;
		email = aEmail;
		password = aPassword;
		photo = aPhoto;
	}

	public UserProfile() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setName(String aName) {
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public boolean setEmail(String aEmail) {
		boolean wasSet = false;
		email = aEmail;
		wasSet = true;
		return wasSet;
	}

	public boolean setPassword(String aPassword) {
		boolean wasSet = false;
		password = aPassword;
		wasSet = true;
		return wasSet;
	}

	public boolean setPhoto(String aPhoto) {
		boolean wasSet = false;
		photo = aPhoto;
		wasSet = true;
		return wasSet;
	}

	public boolean setUserId(int aUserId) {
		boolean wasSet = false;
		userId = aUserId;
		wasSet = true;
		return wasSet;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoto() {
		return photo;
	}

	public int getUserId() {
		return userId;
	}
	/* Code from template association_GetMany */

	public void delete() {
	}
}