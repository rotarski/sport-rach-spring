package com.sport.rach.model.pracownik;

import java.util.List;


public class Pracownik {

	Long id;
	String imie;
	String drugieImie;
	String nazwisko;
	Integer pesel;
	PracownikAdres adres;
	String imieOjca;
	String imieMatki;
	String urzadSkarbowy;
	List<Umowa> umowy;
}
