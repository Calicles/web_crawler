package com.antoine.entity;

import java.util.List;

import database.PalmaresDAO;

public class Palmares extends AbstractEntity {
	
	
	private int id;
	private String[] horses;

	public Palmares(Horse[] horses) {
		id= PalmaresDAO.idAuto;
		int i= 0;
		
		for(Horse h:horses) {
			this.horses[i++]= h.getName();
		}
	}
	
	public Palmares() {
		super();
	}

	public int getId() {return this.id;}
	public void setId(int id) {this.id= id;}
	public String[] getHorses() {return this.horses;}
	
	public void setHorses(Horse[] horses) {
		int i= 0;
		for(Horse h:horses) {
			this.horses[i++]= h.getName();
		}
	}
	public void setHorses(List<String> list) {
		int i= 0;
		for(String s:list) {
			horses[i++]= s;
		}
	}
	
	

}
