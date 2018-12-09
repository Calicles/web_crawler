package com.antoine.entity;

public class Participation extends AbstractEntity {
	
	String id_horse, driver_lastName, driver_firstName, redKm;
	int id_race, horse_number, positioning;
	double rating;
	
	public Participation(Horse horse, Driver driver, int race) {
		this.id_horse= horse.getName();
		this.driver_lastName= driver.getLastName();
		this.driver_firstName= driver.getFirstName();
		this.id_race= race;
	}
	
	public Participation() {
		super();
	}

	public int getHorse_number() {return this.horse_number;}
	public void setHorse_number(int horse_number) {this.horse_number= horse_number;}
	public int getPositioning() {return this.positioning;}
	public void setPositioning(int positioning) {this.positioning= positioning;}
	public double getRating() {return this.rating;}
	public void setRating(double rating) {this.rating= rating;}
	public String getRedKm() {return this.redKm;}
	public void setRedKm(String redKm) {this.redKm= redKm;}
	public String getId_horse() {
		return id_horse;
	}
	public void setId_horse(String id_horse) {
		this.id_horse = id_horse;
	}
	public String getDriver_lastName() {
		return driver_lastName;
	}
	public void setDriver_lastName(String driver_lastName) {
		this.driver_lastName = driver_lastName;
	}
	public String getDriver_firstName() {
		return driver_firstName;
	}
	public void setDriver_firstName(String driver_firstName) {
		this.driver_firstName = driver_firstName;
	}
	public int getId_race() {
		return id_race;
	}
	public void setId_race(int id_race) {
		this.id_race = id_race;
	}
	
	

}
