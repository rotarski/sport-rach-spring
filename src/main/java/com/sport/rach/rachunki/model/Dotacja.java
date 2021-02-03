package com.sport.rach.rachunki.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sport.rach.klub.model.Klub;
@Entity
@Table(name = "rach_dotacja")
public class Dotacja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotNull
	private Podmiot darczynca;
	@ManyToOne
	@JoinColumn(name = "klub_id")
	private Klub klub;
	
	@OneToMany(mappedBy = "dotacja", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	private List<KontoDotacja> konta;
	
	@NotNull
	private Boolean rozliczona;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataUmowy;
	@NotNull
	private Double kwotaDotacji;
	
	public Dotacja() {
	}

	public Dotacja(@NotBlank String name, @NotNull Podmiot darczynca, Klub klub, List<KontoDotacja> konta,
			@NotNull Boolean rozliczona, @NotNull Date dataUmowy, @NotNull Double kwotaDotacji) {
		super();
		this.name = name;
		this.darczynca = darczynca;
		this.klub = klub;
		this.konta = konta;
		this.rozliczona = rozliczona;
		this.dataUmowy = dataUmowy;
		this.kwotaDotacji = kwotaDotacji;
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

	public Podmiot getDarczynca() {
		return darczynca;
	}

	public void setDarczynca(Podmiot darczynca) {
		this.darczynca = darczynca;
	}

	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}

	public List<KontoDotacja> getKonta() {
		return konta;
	}

	public void setKonta(List<KontoDotacja> konta) {
		this.konta = konta;
	}

	public Boolean getRozliczona() {
		return rozliczona;
	}

	public void setRozliczona(Boolean rozliczona) {
		this.rozliczona = rozliczona;
	}

	public Date getDataUmowy() {
		return dataUmowy;
	}

	public void setDataUmowy(Date dataUmowy) {
		this.dataUmowy = dataUmowy;
	}

	public Double getKwotaDotacji() {
		return kwotaDotacji;
	}

	public void setKwotaDotacji(Double kwotaDotacji) {
		this.kwotaDotacji = kwotaDotacji;
	}
	
	
}
