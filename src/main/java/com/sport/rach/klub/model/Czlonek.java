package com.sport.rach.klub.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "klub_czlonek")
public class Czlonek implements Serializable{

	private static final long serialVersionUID = -148940617286285394L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Podaj imię")
	private String imie;
	@NotNull(message = "Podaj nazwisko")
	private String nazwisko;
	@NotNull(message = "Podaj pesel")
	@Pattern(regexp = "([0-9]{11}$)", message = "Podaj prawidłowy pesel")
	private String pesel;
	@NotNull(message = "Podaj funkcję")
	private String funkcjaOrganu;
	@ManyToOne
	@JoinColumn(name="id_organ")
	@JsonBackReference
	private Organ organ;
	
	public Czlonek() {
	}

	public Czlonek(@NotNull String imie, @NotNull String nazwisko,
			@NotNull @Pattern(regexp = "([0-9]{11}$)") String pesel, @NotNull String funkcjaOrganu) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.funkcjaOrganu = funkcjaOrganu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getFunkcjaOrganu() {
		return funkcjaOrganu;
	}

	public void setFunkcjaOrganu(String funkcjaOrganu) {
		this.funkcjaOrganu = funkcjaOrganu;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	
	

}
