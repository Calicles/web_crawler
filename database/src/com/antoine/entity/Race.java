package com.antoine.entity;

import java.sql.Date;

import database.RaceDAO;

public class Race extends AbstractEntity {

	int id= RaceDAO.getNextId(), reunion, num_race; 
	Championship championship;
	Hippodrome hippodrome;
	Date date;
	
	public int getId() {return this.id;}
	public void setId(int id) {this.id= id;}
	public int getReunion() {
		return reunion;
	}
	public void setReunion(int reunion) {
		this.reunion = reunion;
	}
	public int getNum_race() {
		return num_race;
	}
	public void setNum_race(int num_race) {
		this.num_race = num_race;
	}
	public Championship getChampionship() {
		return championship;
	}
	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
	public Hippodrome getHippodrome() {
		return hippodrome;
	}
	public void setHippodrome(Hippodrome hippodrome) {
		this.hippodrome = hippodrome;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "id: "+id+", id championship: "+championship.getId()+
				" id hippodrome: "+hippodrome.getId()+", date: "+date.toString();
	}
	
}

