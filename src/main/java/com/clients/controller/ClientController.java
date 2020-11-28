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

import com.clients.dto.ClientDTO;
import com.clients.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody ClientDTO clientRequest) {
    	clientService.create(clientRequest);
	}
	
	@GetMapping("/byName")
    public List<ClientDTO> findByName(@RequestParam(required = true) String name){
    	return clientService.findByName(name);
    }
	
}
