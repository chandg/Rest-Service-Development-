package com.rest.demorest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="myresource")
public class Pearl {

	private String name;
	private int points;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
