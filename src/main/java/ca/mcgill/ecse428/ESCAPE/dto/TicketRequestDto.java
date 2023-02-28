package ca.mcgill.ecse428.ESCAPE.dto;

public class TicketRequestDto {

	// attributes
	private String name;
	private int price;
	private int eventId;
	private String attendeeEmail;
	
	public TicketRequestDto(String name, int price, int eventId, String attendeeEmail) {
		super();
		this.name = name;
		this.price = price;
		this.eventId = eventId;
		this.attendeeEmail = attendeeEmail;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getAttendeeEmail() {
		return attendeeEmail;
	}
	public void setAttendeeEmail(String attendeeEmail) {
		this.attendeeEmail = attendeeEmail;
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
