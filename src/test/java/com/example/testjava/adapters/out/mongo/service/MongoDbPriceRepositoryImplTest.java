package com.example.testjava.adapters.out.mongo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.testjava.adapters.out.mongo.entities.MongoDBPriceEntity;
import com.example.testjava.adapters.out.mongo.repository.MongoDBRepository;
import com.example.testjava.ports.out.PriceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MongoDbPriceRepositoryImplTest {

	@Qualifier("mongo")
	@Autowired
	PriceRepository priceRepository;

	@MockBean
	MongoDBRepository mongoDbRepository;

	@BeforeEach
	public void init() throws JsonMappingException, JsonProcessingException, ParseException {

		String json = "{\"priceList\":4,\"brandId\":1,\"startDate\":\"2020-06-15T16:00:00.000\",\"endDate\":\"2020-12-31T23:59:59.59\",\"productId\":35455,\"priority\":1,\"price\":38.95,\"curr\":\"EUR\"}";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

		MongoDBPriceEntity priceEntity = objectMapper.readValue(json, MongoDBPriceEntity.class);

		when(mongoDbRepository.findCustom(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1,
				1, PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "priceList"))))
						.thenReturn(Arrays.asList(priceEntity));
		when(mongoDbRepository.findCustom(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1,
				1, PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"))))
						.thenReturn(Arrays.asList(priceEntity));

	}

	@Test
	public void findAll() throws ParseException {
		assertThat(
				priceRepository.findAll(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
						.isNotEmpty();
	}

	@Test
	public void findOne() throws ParseException {
		assertThat(
				priceRepository.findOne(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/07/2020 00:00:00"), 1, 1))
						.isNotNull();
	}

}
