package com.clients.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;


public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 3177290092584386121L;
	
	@NotNull
	private String name;
	private String gender;
	private String birth;
	@NotNull
	private Integer cityId;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
