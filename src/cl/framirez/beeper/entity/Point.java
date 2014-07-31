package cl.framirez.beeper.entity;

public class Point {
	private String name;
	private String latitude;
	private String longitude;
	
	public Point(String n, String la, String lon){
		this.name = n;
		this.latitude = la;
		this.longitude=lon;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
}
