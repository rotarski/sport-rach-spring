package com.sport.rach.klub.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Embeddable
public class Adres implements Serializable{

	private static final long serialVersionUID = 3278518821685872673L;
	@NotNull(message = "Podaj miejscowość")
	private String miejscowosc;
	private String ulica;
	private String numer;
	@Pattern(regexp = "([0-9]{5}$)", message = "Podaj kod xxxxx")
	private String kod;
	
	public Adres() {
	}

	public Adres(@NotNull String miejscowosc, String ulica, String numer, @Pattern(regexp = "([0-9]{5})") String kod) {
		super();
		this.miejscowosc = miejscowosc;
		this.ulica = ulica;
		this.numer = numer;
		this.kod = kod;
	}



	public String getMiejscowosc() {
		return miejscowosc;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getNumer() {
		return numer;
	}

	public void setNumer(String numer) {
		this.numer = numer;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}
	
	

}
