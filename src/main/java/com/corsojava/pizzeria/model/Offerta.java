package com.corsojava.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Offerta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate inizioOff;

	private LocalDate fineOff;

	private String titolo;

	@ManyToOne
	private Pizza pizza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getInizioOff() {
		return inizioOff;
	}

	public void setInizioOff(LocalDate inizioOff) {
		this.inizioOff = inizioOff;
	}

	public LocalDate getFineOff() {
		return fineOff;
	}

	public void setFineOff(LocalDate fineOff) {
		this.fineOff = fineOff;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
