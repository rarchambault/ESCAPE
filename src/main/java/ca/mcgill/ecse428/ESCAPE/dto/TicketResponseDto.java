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
	
}
