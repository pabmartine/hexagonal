package com.example.testjava.adapters.in.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RestPricesControllerIntegTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	RestPricesController restPricesController;

	@Test
	public void findOne10H14D() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "14/06/2020 10:00:00")
				.param("product", "35455")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList", is(Integer.valueOf(1))));
	}

	@Test
	public void findOne16H14D() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "14/06/2020 16:00:00")
				.param("product", "35455")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList", is(Integer.valueOf(2))));
	}

	@Test
	public void findOne21Pm14Day() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "14/06/2020 21:00:00")
				.param("product", "35455")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList", is(Integer.valueOf(1))));
	}

	@Test
	public void findOne10Pm15Day() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "15/06/2020 10:00:00")
				.param("product", "35455")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList", is(Integer.valueOf(3))));
	}

	@Test
	public void findOne21Pm16Day() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "16/06/2020 21:00:00")
				.param("product", "35455")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList", is(Integer.valueOf(4))));
	}

}
