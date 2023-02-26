package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Event;

public class EventResponseDto {

	private int id;	
	private String name;
	private String description;
	private double ticketPrice;
	
	public EventResponseDto(Event event) {
		this.id = event.getId();
		this.name = event.getName();
		this.description = event.getDescription();
		this.ticketPrice = event.getTicketPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
