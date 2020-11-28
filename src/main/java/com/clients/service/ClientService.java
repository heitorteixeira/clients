package com.clients.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.dto.ClientDTO;
import com.clients.exception.CityNotFoundException;
import com.clients.model.City;
import com.clients.model.Client;
import com.clients.repository.CityRepository;
import com.clients.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	public void create(ClientDTO clientRequest) {
		Optional<City> city = cityRepository.findById(clientRequest.getCityId());
		if (!city.isPresent()) {
			throw new CityNotFoundException();
		}
		
		Client client = new Client();
		client.setName(clientRequest.getName());
		client.setGender(clientRequest.getGender());
		client.setBirth(LocalDate.parse(clientRequest.getBirth(), dateFormat));
		client.setCity(city.get());
		clientRepository.save(client);
	}

	public List<ClientDTO> findByName(String name) {
		List<Client> clients = clientRepository.findByName(name);

		return clients.stream()
				.map(c-> {
					ClientDTO clientDTO = new ClientDTO();
					clientDTO.setName(c.getName());
					clientDTO.setGender(c.getGender());
					clientDTO.setCityId(c.getCity().getId());
					clientDTO.setBirth(c.getBirth().format(dateFormat));
					return clientDTO;
				}).collect(Collectors.toList());
	}

}
