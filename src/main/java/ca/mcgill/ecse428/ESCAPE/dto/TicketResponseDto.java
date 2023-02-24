package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Ticket;

public class TicketResponseDto {

	private int ticketId;
	private String name;
	private double price;
	
	public TicketResponseDto(Ticket ticket) {
		this.ticketId = ticket.getTicketId();
		this.name = ticket.getName();
		this.price = ticket.getPrice();
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
}
