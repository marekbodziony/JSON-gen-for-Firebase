package generator.model;

import java.util.GregorianCalendar;

// general application item class

public class TouristicItem {

	// fields
	private TouristicItemType type;
	private String name;
	private GregorianCalendar date;
	private String description; 
	private String www;
	private float gpsLat;
	private float gpsLon;
	private float rating;
	private long likes;
	
	public TouristicItem(TouristicItemType type, String name, GregorianCalendar date, String description, String www, float gpsLat, float gpsLon, float rating, long likes){
		this.type = type;
		this.name = name;
		this.date = date;
		this.description = description;
		this.www = www;	
		this.gpsLat = gpsLat;
		this.gpsLon = gpsLon;
		this.rating = rating;
		this.likes = likes;
	}
	
	
	// getters and setters
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
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	public float getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(float gpsLat) {
		this.gpsLat = gpsLat;
	}
	public float getGpsLon() {
		return gpsLon;
	}
	public void setGpsLon(float gpsLon) {
		this.gpsLon = gpsLon;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public TouristicItemType getType() {
		return type;
	}
	public void setType(TouristicItemType type) {
		this.type = type;
	}
	public GregorianCalendar getDate() {
		return date;
	}
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}		
}
