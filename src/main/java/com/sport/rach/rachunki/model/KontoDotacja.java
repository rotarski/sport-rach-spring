package com.sport.rach.rachunki.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="rach_kontodotacja")
public class KontoDotacja implements Serializable{
	
	private static final long serialVersionUID = 9097987020735586616L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	
	@ManyToOne
	@JoinColumn(name="dotacja_id")
	private Dotacja dotacja;
	
	@NotNull
	private Double kwota;
	
	@ElementCollection
	@CollectionTable(name = "rach_kontodotacja_koszty")
	@Column(name = "kwota")
	@JoinColumn(name = "konto_id")
	@MapKeyJoinColumn(name ="koszt_id")
	private Map<KosztyZd, Double> koszty;
	
	public KontoDotacja() {
	}



	public KontoDotacja(String name, Dotacja dotacja, Double kwota) {
		super();
		this.name = name;
		this.dotacja = dotacja;
		this.kwota = kwota;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dotacja getDotacja() {
		return dotacja;
	}

	public void setDotacja(Dotacja dotacja) {
		this.dotacja = dotacja;
	}

	public Map<KosztyZd, Double> getKoszty() {
		return koszty;
	}

	public void setKoszty(Map<KosztyZd, Double> koszty) {
		this.koszty = koszty;
	}
	
	

	public Double getKwota() {
		return kwota;
	}



	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	
}
