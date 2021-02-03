package com.sport.rach.rachunki.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sport.rach.klub.model.Klub;

@Entity
@Table(name="rach_przychody")
public class PrzychodyZd extends Zdarzenie implements Serializable{


	private static final long serialVersionUID = 2160114017399012147L;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="rodzaj_przych")
	private Przychody przychody;
	
	public PrzychodyZd() {
		super();
	}
	
	public PrzychodyZd(
			Podmiot podmiot, 
			Date dataZdarzenia, 
			String nrDowoduKsiegowego,
			String opisZdarzenia, 
			Double kwota, 
			Klub klub,
			Przychody przychody
			) {
		
		super(podmiot, dataZdarzenia, nrDowoduKsiegowego, opisZdarzenia, kwota, klub);
		this.przychody = przychody;
	}

	public Przychody getPrzychody() {
		return przychody;
	}

	public void setPrzychody(Przychody przychody) {
		this.przychody = przychody;
	}
	
	
}
