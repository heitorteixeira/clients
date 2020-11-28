package com.clients.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clients.dto.CityDTO;
import com.clients.enumeration.StateEnum;
import com.clients.model.City;
import com.clients.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;
	
    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody CityDTO cityRequest) {
    	City city = cityService.create(cityRequest);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(city.getId()).toUri();
    	return ResponseEntity.created(uri).build();
	}
    
    @GetMapping("/byName")
    public ResponseEntity<List<CityDTO>> findByName(@RequestParam(required = true) String name){
    	List<CityDTO> listCityDto = cityService.findByName(name);
    	return ResponseEntity.ok(listCityDto);
    }
	
    @GetMapping("/byState")
    public ResponseEntity<List<CityDTO>> findByState(@RequestParam(required = true) StateEnum state){
    	List<CityDTO> listCityDto = cityService.findByState(state);
    	return ResponseEntity.ok(listCityDto);
    }
}
