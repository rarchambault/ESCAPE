package ca.mcgill.ecse428.ESCAPE.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import javax.persistence.*;

import java.util.*;

// line 15 "model.ump"
// line 89 "model.ump"
@Entity
public class Attendee extends UserProfile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Attendee(String aName, String aEmail, String aPassword, String aPhoto, int aUserId)
  {
    super(aName, aEmail, aPassword, aPhoto, aUserId);
  }

  public Attendee() {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}