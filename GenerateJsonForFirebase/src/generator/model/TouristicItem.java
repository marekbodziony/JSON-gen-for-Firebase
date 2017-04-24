package generator.model;

// general application item class

public abstract class TouristicItem {

	// fields
	private String name;
	private String description; 
	private String www;
	private String gpsLat;
	private String gpsLon;
	private float rating;
	private long likes;
	
	public TouristicItem(String name, String description, String www, String gpsLat, String gpsLon, float rating, long likes){
		this.name = name;
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
	public String getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(String gpsLat) {
		this.gpsLat = gpsLat;
	}
	public String getGpsLon() {
		return gpsLon;
	}
	public void setGpsLon(String gpsLon) {
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
}
