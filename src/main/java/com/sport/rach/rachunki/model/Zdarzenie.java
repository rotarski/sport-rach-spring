package com.sport.rach.rachunki.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sport.rach.klub.model.Klub;

@MappedSuperclass
public class Zdarzenie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "podmio_id")
	private Podmiot podmiot;
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataZdarzenia;

	private String nrDowoduKsiegowego;
	
	private String opisZdarzenia;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "klub_id")
	private Klub klub;
	@NotNull
	private Double kwota;
	
	public Zdarzenie() {
	}

	public Zdarzenie( Podmiot podmiot, @Past @NotNull Date dataZdarzenia, String nrDowoduKsiegowego,
			String opisZdarzenia, @NotNull Double kwota, Klub klub) {
		super();
		this.podmiot = podmiot;
		this.dataZdarzenia = dataZdarzenia;
		this.nrDowoduKsiegowego = nrDowoduKsiegowego;
		this.opisZdarzenia = opisZdarzenia;
		this.kwota = kwota;
		this.klub = klub;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Podmiot getPodmiot() {
		return podmiot;
	}

	public void setPodmiot(Podmiot podmiot) {
		this.podmiot = podmiot;
	}

	public Date getDataZdarzenia() {
		return dataZdarzenia;
	}

	public void setDataZdarzenia(Date dataZdarzenia) {
		this.dataZdarzenia = dataZdarzenia;
	}

	public String getNrDowoduKsiegowego() {
		return nrDowoduKsiegowego;
	}

	public void setNrDowoduKsiegowego(String nrDowoduKsiegowego) {
		this.nrDowoduKsiegowego = nrDowoduKsiegowego;
	}

	public String getOpisZdarzenia() {
		return opisZdarzenia;
	}

	public void setOpisZdarzenia(String opisZdarzenia) {
		this.opisZdarzenia = opisZdarzenia;
	}

	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}
	
	
	
	
}
