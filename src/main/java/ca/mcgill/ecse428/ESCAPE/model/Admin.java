package ca.mcgill.ecse428.ESCAPE.model;
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import jakarta.persistence.Entity;

import java.util.*;

// line 12 "model.ump"
// line 84 "model.ump"
@Entity
public class Admin extends UserProfile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aName, String aEmail, String aPassword)
  {
    super(aName, aEmail, aPassword);
  }

  public Admin() {
    super();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}