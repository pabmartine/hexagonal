package com.example.testjava.adapters.in.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.testjava.domain.Price;
import com.example.testjava.ports.in.PriceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class RestPricesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	RestPricesController restPricesController;

	@MockBean
	private PriceService priceService;

	@BeforeEach
	void init() throws ParseException, JsonMappingException, JsonProcessingException {
		
		String json = "{\"priceList\":4,\"brandId\":1,\"startDate\":\"2020-06-15T16:00:00.000\",\"endDate\":\"2020-12-31T23:59:59.59\",\"productId\":35455,\"priority\":1,\"price\":38.95,\"curr\":\"EUR\"}";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

		
		Price price = objectMapper.readValue(json, Price.class);

		when(priceService.findAll(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
				.thenReturn(Arrays.asList(price));
		when(priceService.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
				.thenReturn(price);
		when(priceService.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/1979 00:00:00"), 1, 1))
		.thenThrow(new MethodArgumentTypeMismatchException(null, null, "date", null, new ParseException(null, 0)));
		when(priceService.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("02/01/1979 00:00:00"), 1, 1))
		.thenThrow(new MethodArgumentTypeMismatchException(null, null, "date", null, new NumberFormatException()));
		when(priceService.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("03/01/1979 00:00:00"), 1, 1))
		.thenThrow(NullPointerException.class);

	}

	@Test
	public void findAll() throws Exception {
		mockMvc.perform(get("/prices/all").param("date", "01/07/2020 00:00:00")
				.param("product", "1")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].startDate", is("15/06/2020 16:00:00")));
	}

	@Test
	public void findOne() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "01/07/2020 00:00:00")
				.param("product", "1")
				.param("brand", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.startDate", is("15/06/2020 16:00:00")));
	}
	
	@Test
	public void resourceNotFoundExceptionByDate() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "01/01/1979 00:00:00")
				.param("product", "1")
				.param("brand", "1"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void resourceNotFoundExceptionByNumber() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "02/01/1979 00:00:00")
				.param("product", "1")
				.param("brand", "1"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void globalExceptionHandler() throws Exception {
		mockMvc.perform(get("/prices/one").param("date", "03/01/1979 00:00:00")
				.param("product", "1")
				.param("brand", "1"))
				.andExpect(status().isInternalServerError());
	}


}
