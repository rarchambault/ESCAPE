package ca.mcgill.ecse428.ESCAPE.service;
import ca.mcgill.ecse428.ESCAPE.dao.AdminRepository;
import ca.mcgill.ecse428.ESCAPE.dao.UserProfileRepository;
import ca.mcgill.ecse428.ESCAPE.dao.AdminRepository;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserProfileService {

  @Autowired
  UserProfileRepository UserProfileRepository;

  // creating user profile
  @Transactional
  public UserProfile createUserProfile(
    String name,
    String email,
    String password,
    String photo,
    int userId
  ) {
    UserProfile currentUserProfile = UserProfileRepository.findUserProfileByName(
      name
    );
    Admin currentAdmin = AdminRepository.findAdminProfileByName(
      name
    );
  }