package com.antoine.entity;

public class Horse extends AbstractEntity {
	
	private String name, age_sex, robe, owner, coach, perf;
	private Horse father, mother;
	private double money_won;
	
	public Horse() {
		super();
	}
	
	
	public Horse(String name) {
		this.name= name;
		robe= null;
		owner= null;
		coach= null;
		perf= null;
		father= null;
		mother= null;
		money_won= 0;
	}


	public String getName() {return this.name;}
	public String getAge_sex() {return this.age_sex;}
	public Horse getFather() {return this.father;}
	public Horse getMother() {return this.mother;}
	public String getRobe() {return this.robe;}
	public String getOwner() {return this.owner;}
	public String getCoach() {return this.coach;}
	public double getMoney_won() {return this.money_won;}
	public String getPerf() {return this.perf;}
	
	public void setName(String name) { this.name= name;}
	public void setAge_sex(String age_sex) {this.age_sex= age_sex;}
	public void setFather(Horse father) {this.father= father;}
	public void setMother(Horse mother) {this.mother= mother;}
	public void setOwner(String owner) {this.owner= owner;}
	public void setCoach(String coach) {this.coach= coach;}
	public void setRobe(String robe) {this.robe= robe;}
	public void setMoney(double money_won) {this.money_won= money_won;}
	public void setPerf(String perf) {this.perf= perf;}

}
