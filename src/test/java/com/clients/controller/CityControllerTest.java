package com.clients.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.clients.dto.CityDTO;
import com.clients.enumeration.StateEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static final String URL = "/city";
	
	private String getJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	@Test
	public void create() throws Exception{
		CityDTO cityRequest = new CityDTO();
		cityRequest.setName("Porto Alegre");
		cityRequest.setState(StateEnum.RS);
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJson(cityRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
	}
	
	@Test
	public void findCityByNameNotFound() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "/byName?name=Carazinho"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void findCityByName() throws Exception{
		String expected = "["
                + "{\"name\": \"Flordenapolis\",\"state\": \"SC\"},"
                + "{\"name\": \"Florianopolis\",\"state\": \"SC\"}"
                + "]";
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/byName?name=Flor"))
				.andExpect(status().isOk())
				.andReturn();

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}
	
	@Test
	public void findCityByStateName() throws Exception{
		String expected = "["
                + "{\"name\": \"Flordenapolis\",\"state\": \"SC\"},"
                + "{\"name\": \"Florianopolis\",\"state\": \"SC\"},"
				+ "{\"name\": \"Garopaba\",\"state\": \"SC\"}"
                + "]";
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/byState?state=SC"))
				.andExpect(status().isOk())
				.andReturn();

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}
	
}
