package com.sport.rach.wspolne;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@MappedSuperclass
public class Adres {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotNull
	String miejscowosc;
	String ulica;
	String numer;
	@Pattern(regexp = "[0-9] {5}")
	String kod;
	
	public Adres() {
	}

	public Adres(@NotNull String miejscowosc, String ulica, String numer, @Pattern(regexp = "[0-9] {5}") String kod) {
		super();
		this.miejscowosc = miejscowosc;
		this.ulica = ulica;
		this.numer = numer;
		this.kod = kod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
