package ca.mcgill.ecse428.ESCAPE.dto;

public class TicketRequestDto {

	// attributes
	private String name;
	private int price;
	private int eventId;
	
	public TicketRequestDto(String name, int price, int eventId) {
		super();
		this.name = name;
		this.price = price;
		this.eventId = eventId;
	}
	
	public TicketRequestDto() {} // default constructor for jackson parsing
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


}