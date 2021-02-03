package com.sport.rach.rachunki.dto;

import java.io.Serializable;
import java.util.List;

import com.sport.rach.rachunki.model.Koszty;
import com.sport.rach.rachunki.model.Podmiot;

public class KosztyDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Podmiot> podmiotList;
	
	private List<Koszty> kosztyList;
	
	public KosztyDto() {
	}
	
	

}
