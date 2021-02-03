package com.sport.rach.main.model;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="main_item")
public class ItemContainer extends AbstractContainer{

	private static final long serialVersionUID = -6793004691896093674L;
	
	public ItemContainer() {
	}
	
	public ItemContainer(String title, String content, int orderId) {
		super(title, content, orderId);
	}

	
	
	

}
