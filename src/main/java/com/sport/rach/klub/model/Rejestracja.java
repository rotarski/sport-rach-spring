package com.sport.rach.klub.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Rejestracja implements Serializable{

	private static final long serialVersionUID = -5393928319022911647L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotNull
	String nazwa;
	@NotNull
	AdresRejestr adres;
	@NotNull
	Integer numerIdentyfikacyjny;
	
	public Rejestracja() {
	}

	public Rejestracja(@NotNull String nazwa, @NotNull AdresRejestr adres, @NotNull Integer numerIdentyfikacyjny) {
		super();
		this.nazwa = nazwa;
		this.adres = adres;
		this.numerIdentyfikacyjny = numerIdentyfikacyjny;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public AdresRejestr getAdres() {
		return adres;
	}

	public void setAdres(AdresRejestr adres) {
		this.adres = adres;
	}

	public Integer getNumerIdentyfikacyjny() {
		return numerIdentyfikacyjny;
	}

	public void setNumerIdentyfikacyjny(Integer numerIdentyfikacyjny) {
		this.numerIdentyfikacyjny = numerIdentyfikacyjny;
	}
	
	
	

}
