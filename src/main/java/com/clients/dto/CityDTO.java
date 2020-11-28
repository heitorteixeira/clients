package com.clients.dto;

import java.io.Serializable;

import com.clients.enumeration.StateEnum;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = 7386579464384317353L;
	
	private String name;
	private StateEnum state;
	
	public CityDTO() {
		
	}
	
	public CityDTO(String name, StateEnum state) {
		this.name = name;
		this.state = state;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}
	
}
