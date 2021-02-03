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
@Table(name="rach_koszty")
public class KosztyZd extends Zdarzenie implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="rodzaj_kosztow")
	private Koszty koszty;
	
	public KosztyZd() {
		super();
	}

	public KosztyZd(

			Podmiot podmiot, 
			Date dataZdarzenia, 
			String nrDowoduKsiegowego,
			String opisZdarzenia, 
			Double kwota, 
			Klub klub,
			Koszty koszty) {
		
		super( podmiot, dataZdarzenia, nrDowoduKsiegowego, opisZdarzenia, kwota,klub);
		this.koszty = koszty;
	}

	public Koszty getKoszty() {
		return koszty;
	}

	public void setKoszty(Koszty koszty) {
		this.koszty = koszty;
	}
	
	

}
