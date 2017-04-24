package generator.model;

import java.util.Date;

public class Event extends TouristicItem{
	
	public Event(String name, String description, String www, String gpsLat, String gpsLon, float rating, int likes) {
		super(name, description, www, gpsLat, gpsLon, rating, likes);
	}

	private Date eventDate;
	
	

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	

}
