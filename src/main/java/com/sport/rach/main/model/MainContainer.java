package com.sport.rach.main.model;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="main_main")
public class MainContainer extends AbstractContainer{

	private static final long serialVersionUID = -6793004691896093674L;
	
	public MainContainer() {
	}
	
	public MainContainer(String title, String content, int orderId) {
		super(title, content, orderId);
	}

}
