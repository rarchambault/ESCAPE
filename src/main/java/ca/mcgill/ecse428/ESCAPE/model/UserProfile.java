package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.*;

import java.util.*;

// line 2 "model.ump"
// line 77 "model.ump"
@MappedSuperclass
public class UserProfile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserProfile Attributes
  private String name;
  @Id
  private String email;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserProfile(String aName, String aEmail, String aPassword)
  {
    name = aName;
    email = aEmail;
    password = aPassword;
  }

  public UserProfile() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {
  }
}