package com.clients.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.dto.CityDTO;
import com.clients.enumeration.StateEnum;
import com.clients.model.City;
import com.clients.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public void create(CityDTO cityRequest) {
		
		City city = new City();
		city.setName(cityRequest.getName());
		city.setState(cityRequest.getState());
		cityRepository.save(city);
		
	}

	public List<CityDTO> findByName(String name) {
		List<City> cities = cityRepository.findByName(name);
		
		return cities.stream().map(c-> new CityDTO(c.getName(), c.getState())).collect(Collectors.toList());
	}

	public List<CityDTO> findByState(StateEnum state) {
		List<City> cities = cityRepository.findByStateOrderByName(state);
		
		return cities.stream().map(c-> new CityDTO(c.getName(), c.getState())).collect(Collectors.toList());
	}

	
	
}
