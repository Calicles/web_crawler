package com.antoine.entity;

import database.ChampionshipDAO;

public class Championship extends AbstractEntity {
	
	private int id= ChampionshipDAO.getNextId();
	private String name, challenge_type;
	private double price_money;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {this.id= id;}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChallenge_type() {
		return challenge_type;
	}
	public void setChallenge_type(String challenge_type) {
		this.challenge_type = challenge_type;
	}
	public double getPrice_money() {
		return price_money;
	}
	public void setPrice_money(double price_money) {
		this.price_money = price_money;
	}
	
	@Override
	public String toString() {
		return "id: "+id+", name: "+name+", challenge type: "+challenge_type+", price money: "+price_money;
	}
	

}
