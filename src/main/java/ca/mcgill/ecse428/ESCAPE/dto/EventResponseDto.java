package ca.mcgill.ecse428.ESCAPE.dto;

import ca.mcgill.ecse428.ESCAPE.model.Event;

import java.time.LocalDateTime;

public class EventResponseDto {

	private int id;	
	private String name;
	private String description;
	private double ticketPrice;

	private int capacity;
	private LocalDateTime startTime;
	
	public EventResponseDto(Event event) {
		this.id = event.getId();
		this.name = event.getName();
		this.description = event.getDescription();
		this.ticketPrice = event.getTicketPrice();
		this.capacity = event.getCapacity();
		this.startTime = event.getStartTime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getCapacity() { return capacity; }

	public void setCapacity(int capacity) { this.capacity = capacity; }

	public LocalDateTime getStartTime() { return startTime; }

	public void setStartTime(LocalDateTime startTime){ this.startTime = startTime; }
}
