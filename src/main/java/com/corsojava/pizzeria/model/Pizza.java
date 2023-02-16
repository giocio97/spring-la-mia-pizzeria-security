package com.corsojava.pizzeria.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pizze")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotEmpty(message = "Questo è un campo obbligatorio")
	@Size(min = 2, max = 20, message = "il nome  deve contere dai {min} ai {max} caratteri")
	private String nome;

	@NotNull
	@NotEmpty(message = "Questo è un campo obbligatorio")
	private String descrizione;

	private String foto;
	@NotNull(message = "Questo è un campo obbligatorio")
//	@NotEmpty(message = "Questo è un campo obbligatorio")
	@DecimalMin(value = "1.00", message = "il prezzo deve essere maggiore di zero")
	private BigDecimal prezzo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getId() {
		return id;
	}

}
