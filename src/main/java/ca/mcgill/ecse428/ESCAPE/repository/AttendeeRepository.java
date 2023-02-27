package ca.mcgill.ecse428.ESCAPE.repository;

import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface AttendeeRepository extends CrudRepository<Attendee, String> {

	public Attendee findAttendeeByEmail(String email);
}