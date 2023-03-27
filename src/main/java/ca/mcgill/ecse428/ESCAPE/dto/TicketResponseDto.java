package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Attendee;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketResponseDto {

	private int ticketId;
	private String name;
	private double price;

	private List<String> attendeeEmails;
	
	public TicketResponseDto(Ticket ticket) {
		this.ticketId = ticket.getTicketId();
		this.name = ticket.getName();
		this.price = ticket.getPrice();
		this.attendeeEmails = new ArrayList<String>();
		for (Attendee attendee : ticket.getAttendees()){
			attendeeEmails.add(attendee.getEmail());
		}
	}

	public TicketResponseDto(int ticketId, String name, double price) {
		super();
		this.ticketId = ticketId;
		this.name = name;
		this.price = price;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getAttendeeEmails() { return attendeeEmails; }

	public String getAttendeeEmail(int index) { return attendeeEmails.get(index); }
	
}
