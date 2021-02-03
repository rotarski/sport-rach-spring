package com.sport.rach.main.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public class AbstractContainer implements Serializable{
	
	private static final long serialVersionUID = 1199865610121775003L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Wpisz tytuł")
	private String title;
	@NotBlank(message = "Dodaj treść")
	@Lob
	private String content;
	@NotNull(message = "Podaj kolejność, cyfra - typ sortowania desc")
	private Integer sortId;
	
	public AbstractContainer() {
	}

	public AbstractContainer(@NotBlank String title, @NotBlank String content, @NotNull Integer orderId) {
		super();
		this.title = title;
		this.content = content;
		this.sortId = orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer orderId) {
		this.sortId = orderId;
	}
	
	
	

}
