package com.antoine.entity;

import database.HippodromeDAO;

public class Hippodrome extends AbstractEntity {

	private int id= HippodromeDAO.getNextId();
	private String name, town, country, track_type;
	private double length;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {this.id= id;}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTrack_type() {
		return track_type;
	}
	public void setTrack_type(String track_type) {
		this.track_type = track_type;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "id: "+id+", name: "+name+", town: "+town+", track type: "+track_type+", length: "+length;
	}
	
}
