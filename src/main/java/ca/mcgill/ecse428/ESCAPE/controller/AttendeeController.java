package ca.mcgill.ecse428.ESCAPE.controller;
import ca.mcgill.ecse428.ESCAPE.model.*;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse428.ESCAPE.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attendee")


public class AttendeeController {

  @Autowired
  private AttendeeService attendeeService;

  /***
   *
   * @return list of all items
   */
  @GetMapping(value = { "/all/", "/all" })
  public List<Attendee> getAllAttendees() {
    Iterable<Attendee> attendees = attendeeService.getAllAttendees();
//    return Attendee
//      .getAll()
//      .stream()
//      .map(lib -> convertToDto(lib))
//      .collect(Collectors.toList());

    return (List) attendees;
  }
    
}
