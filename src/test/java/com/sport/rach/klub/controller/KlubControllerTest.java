package com.sport.rach.klub.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sport.rach.klub.model.Adres;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.klub.service.KlubService;


@WebMvcTest(KlubController.class)
public class KlubControllerTest {
	@Autowired
	private KlubController klubController;
	@MockBean
	private KlubService klubService;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void controllerLoad() {
		assertThat(klubController).isNotNull();
	}
	
	@Test
	public void returnKlubWithTheproperParameter() throws Exception {
		Klub klub = getKlub();
		ObjectMapper mapper = new ObjectMapper();
		String mappedKlub = mapper.writeValueAsString(klub);
		when(klubService.getKlub(1L)).thenReturn(Optional.of(klub));
		
		MvcResult result = this.mockMvc.perform(get("/klub/1"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.id").value(1))
			.andReturn();
		
		assertThat(mappedKlub.equals(result.getResponse().getContentAsString()));
	}
	
	@Test
	public void returnEmptyOptionalWithWrongParameter() throws Exception {
		when(klubService.getKlub(2L)).thenReturn(Optional.empty());
		this.mockMvc.perform(get("/klub/2"))
		.andExpect(status().isNotFound());
	}
	
	private Klub getKlub() {
		Adres klubAdres = new Adres("Jaroszowiec", "Kolejowa","25","32312");
		Klub klub = new Klub("LKS Unia Jaroszowiec", klubAdres, 12345, 56987);
		klub.setId(1L);
		return klub;
	}

}
