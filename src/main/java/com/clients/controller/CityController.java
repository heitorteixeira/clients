package com.clients.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clients.dto.CityDTO;
import com.clients.enumeration.StateEnum;
import com.clients.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;
	
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CityDTO cityRequest) {
    	cityService.create(cityRequest);
	}
    
    @GetMapping("/byName")
    public List<CityDTO> findByName(@RequestParam(required = true) String name){
    	return cityService.findByName(name);
    }
	
    @GetMapping("/byState")
    public List<CityDTO> findByState(@RequestParam(required = true) StateEnum state){
    	return cityService.findByState(state);
    }
}
