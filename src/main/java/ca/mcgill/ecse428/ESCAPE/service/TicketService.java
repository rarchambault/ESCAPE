package ca.mcgill.ecse428.ESCAPE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.ESCAPE.dto.TicketRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.exception.TicketException;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(int ticketId) throws TicketException {
        Optional<Ticket> optionalTicket = ticketRepository.findTicketByTicketId(ticketId);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new TicketException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + id);
        }
    }

    public TicketResponseDto createTicket(TicketRequestDto request) {
    	UserProfile creator = null;
        Event event = null;
    	Ticket Ticket = new Ticket(request.getTicketId(), request.getName(), request.getPrice(), 0, event);
    	ticketRepository.save(Ticket);
    	return new TicketResponseDto(Ticket);
    }

    public void updateTicket(int id, Ticket Ticket) throws TicketException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket.setTicketId(id);
            ticketRepository.save(Ticket);
        } else {
            throw new TicketException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + id);
        }
    }

    public void deleteTicket(int id) throws TicketException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            ticketRepository.delete(optionalTicket.get());
        } else {
            throw new TicketException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + id);
        }
    }
}