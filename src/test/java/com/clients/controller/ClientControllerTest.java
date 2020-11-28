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

import com.clients.dto.ClientDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static final String URL = "/client";
	
	private String getJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	@Test
	public void create() throws Exception{
		ClientDTO clientRequest = new ClientDTO();
		clientRequest.setName("Heitor Teixeira");
		clientRequest.setGender("M");
		clientRequest.setBirth("02/10/1984");
		clientRequest.setCityId(1);
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJson(clientRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
	}
	
	@Test
	public void findCityByNameNotFound() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get(URL + "/byName?name=Fernanda"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void findClientByName() throws Exception{
		String expected = "[\n"
				+ "    {\n"
				+ "        \"name\": \"Heitor Teixeira\",\n"
				+ "        \"gender\": \"M\",\n"
				+ "        \"birth\": \"02/10/1984\",\n"
				+ "        \"cityId\": 1\n"
				+ "    }\n"
				+ "]";
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/byName?name=Heitor"))
				.andExpect(status().isOk())
				.andReturn();

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}
	
	@Test
	public void findClientById() throws Exception{
		String expected = "{\n"
				+ "    \"name\": \"Danusa Teixeira\",\n"
				+ "    \"gender\": \"F\",\n"
				+ "    \"birth\": \"20/10/1987\",\n"
				+ "    \"cityId\": 17\n"
				+ "}";
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/byId/6"))
				.andExpect(status().isOk())
				.andReturn();

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}
	
}
