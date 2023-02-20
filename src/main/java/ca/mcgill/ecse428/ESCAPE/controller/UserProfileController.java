package ca.mcgill.ecse428.ESCAPE.controller;
import ca.mcgill.ecse428.ESCAPE.dto.*;
import ca.mcgill.ecse428.ESCAPE.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/userProfiles")


public class UserProfileController {

  @Autowired
  private UserProfileService userProfileService;

  /***
   *
   * @return list of all items
   */
  @GetMapping(value = { "/all/", "/all" })
  public List<UserProfileDTO> getAllUserProfiles() {
    System.out.println("Flag Get");
    return UserProfile
      .getAll()
      .stream()
      .map(lib -> convertToDto(lib))
      .collect(Collectors.toList());
  }
    
}
