package generator.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Generator {
	
	private TouristicItemType type;	
	private String name = "no data";
	private GregorianCalendar date = null;
	private String description = "no data"; 
	private String www = "no data";
	private float gpsLat;
	private float gpsLon;
	private float rating;
	private long likes;

	private int count = 0;
	
	private JSONObject json;
	private JSONObject eventJSON;
	private JSONObject attractionJSON;
	private JSONObject placeJSON;
	private JSONObject parkJSON;
	private JSONObject playgroundJSON;
	private JSONObject restaurantJSON;
	
	// constructor
	public Generator(){
		json = new JSONObject();
		eventJSON = new JSONObject();
		attractionJSON = new JSONObject();
		placeJSON = new JSONObject();
		parkJSON = new JSONObject();
		playgroundJSON = new JSONObject();
		restaurantJSON = new JSONObject();
	}	
	
	// setters
	public void setType(TouristicItemType type) { 
		this.type = type;
		 changeJson();
		 }
	public void setDate(GregorianCalendar date) 	{ this.date = date;}
	public void setName(String name) 				{ this.name = name;}
	public void setDescription(String description) 	{ this.description = description;}
	public void setWww(String www) 					{ this.www = www;}
	public void setGpsLat(float gpsLat) 			{ this.gpsLat = gpsLat;}
	public void setGpsLon(float gpsLon) 			{ this.gpsLon = gpsLon;}
	public void setRating(float rating) 			{ this.rating = rating;}
	public void setLikes(long likes) 				{ this.likes = likes;}
	public void setJson(JSONObject json) 			{ this.json = json;}

	// getters
	public GregorianCalendar getDate()				{ return date;}
	public int getCount() 							{ return count;}
	public JSONObject getJson() 					{ return json;}

	
	// add item to JSON 
	public void addToJson(){
		
		if (json.names() != null) count = json.names().length();
		count++;
		JSONObject jsonTemp = new JSONObject();
		try {
			jsonTemp.put("name", name);
			if (date != null) jsonTemp.put("date", date.getTimeInMillis());
			else jsonTemp.put("date", 0);
			jsonTemp.put("description", description);
			jsonTemp.put("www",www);			
			jsonTemp.put("gpsLat",gpsLat);
			jsonTemp.put("gpsLon", gpsLon);
			jsonTemp.put("rating", rating);
			jsonTemp.put("likes", likes);
			
			System.out.println(jsonTemp);			
			json.put(""+count, jsonTemp);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// generate JSON file
	public void generateJson(){
		JSONObject firebaseJSON = new JSONObject();
//		JSONObject count = new JSONObject();		// for adding items count number in every type category 
		try{
			firebaseJSON.put(TouristicItemType.EVENT.name(), eventJSON);
			firebaseJSON.put(TouristicItemType.ATTRACTION.name(), attractionJSON);
			firebaseJSON.put(TouristicItemType.PLACE.name(), placeJSON);
			firebaseJSON.put(TouristicItemType.PARK.name(), parkJSON);
			firebaseJSON.put(TouristicItemType.PLAYGROUND.name(), playgroundJSON);
			firebaseJSON.put(TouristicItemType.RESTAURANT.name(), restaurantJSON);
			
//			count.put(TouristicItemType.EVENT.name(), eventJSON.length());
//			count.put(TouristicItemType.ATTRACTION.name(), attractionJSON.length());
//			count.put(TouristicItemType.PLACE.name(), placeJSON.length());
//			count.put(TouristicItemType.PARK.name(), parkJSON.length());
//			count.put(TouristicItemType.PLAYGROUND.name(), playgroundJSON.length());
//			count.put(TouristicItemType.RESTAURANT.name(), restaurantJSON.length());
//			
//			firebaseJSON.put("count",count);
						
		}catch (JSONException e){
			e.printStackTrace();
		}
		System.out.println(firebaseJSON);
		saveJsonToFile(firebaseJSON);
	}
	
	// change actual JSON object depending of type
	public void changeJson(){
		switch (type){
		case EVENT 		:	json = eventJSON; break;
		case ATTRACTION : 	json = attractionJSON; break;
		case PLACE		: 	json = placeJSON; break;
		case PARK		: 	json = parkJSON; break;
		case PLAYGROUND	: 	json = playgroundJSON; break;
		case RESTAURANT	: 	json = restaurantJSON; break;
		}
		if (json.names() != null) count = json.names().length();
		else count = 0;
	}
	
	// save JSON to file
	private void saveJsonToFile(JSONObject j){
		String s = j.toString();
		
		try {
			File f = new File("firebase.json");		
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter out = new OutputStreamWriter(fos);
			Writer w = new BufferedWriter(out); 
			w.write(s);
			System.out.print("OK! JSON file was created! Path=" + f.getAbsolutePath());
			w.close();
		} catch (FileNotFoundException e) {
			System.out.print("ERROR! JSON file wasn't created!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
