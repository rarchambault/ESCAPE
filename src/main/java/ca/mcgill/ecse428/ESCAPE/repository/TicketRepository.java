package ca.mcgill.ecse428.ESCAPE.repository;

import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Attendee, String> {
    public Ticket findTicketByTicketId(int ticketId);
}