package ca.mcgill.ecse428.ESCAPE.dto;

public class RegisterRequestDto {

	// attributes
	private String attendeeEmail;
	private int ticketId;

	public RegisterRequestDto(String attendeeEmail, int ticketId) {
		super();
		this.attendeeEmail = attendeeEmail;
		this.ticketId = ticketId;
	}

	public RegisterRequestDto() {} // default constructor for jackson parsing
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getAttendeeEmail() {
		return attendeeEmail;
	}
	public void setAttendeeEmail(String attendeeEmail) {
		this.attendeeEmail = attendeeEmail;
	}


}
