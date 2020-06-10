package com.soap.consumer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class CountryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id; 
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "capital", nullable = false)
	private String capital;
	
	@Column(name =  "population")
	private int population;
	
	@Column(name = "currency", nullable = false)
	private String currency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}	

}
