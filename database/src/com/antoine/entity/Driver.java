package com.antoine.entity;

import com.antoine.contracts.Entity;

public class Driver extends AbstractEntity implements Entity{
	
	private String lastName;
	private String firstName;
	
	public Driver(String lastName, String firstName) {
		this.lastName= lastName;
		this.firstName= firstName;
	}
	
	public Driver() {
		super();
	}
	
	public String getLastName() {return this.lastName;}
	public String getFirstName() {return this.firstName;}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String toString() {
		return "lastName: "+lastName+", firstName: "+firstName;
	}

}
