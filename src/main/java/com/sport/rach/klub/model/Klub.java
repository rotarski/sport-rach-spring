package com.sport.rach.klub.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Klub implements Serializable{
	
	private static final long serialVersionUID = -5830259318834206526L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Size(min = 3)
	String nazwa;
	
	@NotNull
	@OneToOne
	KlubAdres adres;
	@NotNull
	Integer nip;
	@NotNull
	Integer regon;

	@OneToOne
	Rejestracja rejestracja;
	
	@OneToOne
	Organ zarzad;
	@OneToOne
	Organ nadzor;
	
	public Klub() {
	}

	public Klub(
			@Size(min = 3) String nazwa, 
			@NotNull KlubAdres adres, 
			@NotNull Integer nip, 
			@NotNull Integer regon) {
		super();
		this.nazwa = nazwa;
		this.adres = adres;
		this.nip = nip;
		this.regon = regon;
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

	public KlubAdres getAdres() {
		return adres;
	}

	public void setAdres(KlubAdres adres) {
		this.adres = adres;
	}

	public Integer getNip() {
		return nip;
	}

	public void setNip(Integer nip) {
		this.nip = nip;
	}

	public Integer getRegon() {
		return regon;
	}

	public void setRegon(Integer regon) {
		this.regon = regon;
	}

	public Rejestracja getRejestracja() {
		return rejestracja;
	}

	public void setRejestracja(Rejestracja rejestracja) {
		this.rejestracja = rejestracja;
	}

	public Organ getZarzad() {
		return zarzad;
	}

	public void setZarzad(Organ zarzad) {
		this.zarzad = zarzad;
	}

	public Organ getNadzor() {
		return nadzor;
	}

	public void setNadzor(Organ nadzor) {
		this.nadzor = nadzor;
	}


	
	

}
