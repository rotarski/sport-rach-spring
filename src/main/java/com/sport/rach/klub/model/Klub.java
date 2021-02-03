package com.sport.rach.klub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Klub implements Serializable{
	
	private static final long serialVersionUID = -5830259318834206526L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, message = "Podah nazwę klubu min 3 znaki")
	private String nazwa;
	@Valid
	@NotNull(message = "Podaj adres")
	@Embedded
	private Adres adres;
	@NotNull(message = "Podaj nip")
	private Integer nip;
	@NotNull(message = "Podaj regon")
	private Integer regon;

	@OneToOne(cascade = {CascadeType.ALL})
	private Rejestracja rejestracja;

	@OneToOne(cascade = {CascadeType.ALL})
	private Organ zarzad;

	@OneToOne(cascade = {CascadeType.ALL})
	private Organ nadzor;
	@Version
	private long version;
	
	public Klub() {
	}

	public Klub(
			@Size(min = 3) String nazwa, 
			@NotNull Adres adres, 
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

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}


	
	

}
