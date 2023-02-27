package ca.mcgill.ecse428.ESCAPE.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class TicketException extends RuntimeException {

	private HttpStatus status;
	
	public TicketException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}