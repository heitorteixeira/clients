package com.clients.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clients.dto.ClientDTO;
import com.clients.model.Client;
import com.clients.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody ClientDTO clientRequest) {
    	Client client = clientService.create(clientRequest);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(client.getId()).toUri();
    	return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/byName")
    public ResponseEntity<List<ClientDTO>> findByName(@RequestParam(required = true) String name){
		List<ClientDTO> listClientDTO = clientService.findByName(name);
		return ResponseEntity.ok(listClientDTO);
    }
	
	@GetMapping("/byId/{clientId}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer clientId){
		return ResponseEntity.ok(clientService.findById(clientId));
    }
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Integer clientId){
		clientService.delete(clientId);
		return ResponseEntity.noContent().build();
	}
	
}
