package com.example.testjava.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.testjava.ports.in.PriceService;
import com.example.testjava.ports.out.PriceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class PriceServiceImplTest {

	@Autowired
	PriceService priceService;

	@Qualifier("h2")
	@MockBean
	PriceRepository priceRepository;

	@BeforeEach
	public void init() throws JsonMappingException, JsonProcessingException, ParseException {

		String json = "{\"priceList\":4,\"brandId\":1,\"startDate\":\"2020-06-15T16:00:00.000\",\"endDate\":\"2020-12-31T23:59:59.59\",\"productId\":35455,\"priority\":1,\"price\":38.95,\"curr\":\"EUR\"}";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

		Price price = objectMapper.readValue(json, Price.class); 

		when(priceRepository.findAll(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
				.thenReturn(Arrays.asList(price));
		when(priceRepository.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
				.thenReturn(Optional.of(price));
	}

	@Test
	public void findAll() throws ParseException {
		assertThat(priceService.findAll(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
				.isNotEmpty();
	}

	@Test
	public void findOne() throws ParseException {
		assertThat(priceService.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
				.isNotNull();
	}

}
