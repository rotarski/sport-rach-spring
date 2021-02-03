package com.sport.rach.klub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "klub_organ")
public class Organ implements Serializable{

	private static final long serialVersionUID = -6987207288374920307L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Podaj nazwę")
	private String nazwa;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private OrganTyp organTyp;
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "organ")
	private List<Czlonek> sklad = new ArrayList<>();


	public Organ() {
	}

	public Organ(@NotNull String nazwa, @NotNull OrganTyp organTyp) {
		super();
		this.nazwa = nazwa;
		this.organTyp = organTyp;
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

	public OrganTyp getOrganTyp() {
		return organTyp;
	}

	public void setOrganTyp(OrganTyp organTyp) {
		this.organTyp = organTyp;
	}

	public List<Czlonek> getSklad() {
		return sklad;
	}

	public void setSklad(List<Czlonek> sklad) {
		this.sklad = sklad;
	}


}
