package ca.mcgill.ecse428.ESCAPE.dto;

public class TicketRequestDto {

	// attributes
	private int ticketId;
	private String name;
	private double price;
	
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
