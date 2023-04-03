package ca.mcgill.ecse428.ESCAPE.dto;

public class TicketRequestDto {

	// attributes
	private String name;
	private double price;
	private int eventId;
	
	public TicketRequestDto(String name, double price, int eventId) {
		this.name = name;
		this.price = price;
		this.eventId = eventId;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
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
