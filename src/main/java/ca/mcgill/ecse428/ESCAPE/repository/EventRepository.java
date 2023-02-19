package ca.mcgill.ecse428.ESCAPE.repository;

import ca.mcgill.ecse428.ESCAPE.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer>{

	Event findEventById(int id);
	
}
