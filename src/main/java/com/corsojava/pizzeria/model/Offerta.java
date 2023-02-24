package com.corsojava.pizzeria.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Offerta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Inserire data Inizio offerta - Campo obbligatorio")
	private LocalDate inizioOff;

	@NotNull(message = "Inserire data Fine offerta - Campo obbligatorio")
	private LocalDate fineOff;

	@NotNull(message = "Inserire titolo - Campo obbligatorio")
	@NotEmpty(message = "Inserire titolo - Campo obbligatorio")
	private String titolo;

	@JsonIgnore
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
