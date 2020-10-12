package com.sport.rach.model.klub;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Klub {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Size(min = 3)
	String nazwa;
	
	@NotNull
	KlubAdres adres;
	
	Integer nip;
	
	Integer regon;
	
	Rejestracja rejestracja;
	
	Organ zarzad;
	
	Organ nadzor;
	
	
	
	

}
