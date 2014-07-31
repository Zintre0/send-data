package cl.framirez.beeper.entity;

import java.util.ArrayList;

public class Route {
	private String name;
	private String date;
	private String user;
	private String description;
	private ArrayList<Point> array = new ArrayList<Point>();
	
	public Route(String name, String date, String user, String description) {
		this.name = name;
		this.date = date;
		this.user = user;
		this.description = description;
	}

	
}
