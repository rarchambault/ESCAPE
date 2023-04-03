package ca.mcgill.ecse428.ESCAPE.dto;

import java.time.LocalDateTime;

public class EventRequestDto {

	// attributes
	private String name;
	private String description;
	private int capacity;
	private LocalDateTime startTime;

	private String location;

	public EventRequestDto(String name, String description, int capacity, LocalDateTime startTime, String location) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.startTime = startTime;
		this.location = location;
	}

	public EventRequestDto(){};

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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
