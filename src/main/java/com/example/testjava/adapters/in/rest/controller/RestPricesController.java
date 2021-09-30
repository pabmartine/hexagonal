package com.example.testjava.adapters.in.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.testjava.adapters.in.rest.dto.PriceDto;
import com.example.testjava.adapters.in.rest.mapper.PriceDtoMapper;
import com.example.testjava.ports.in.PriceService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controllers class that receives client requests
 * @author pmarti14
 *
 */
@Slf4j
@RestController
@RequestMapping("/prices")
public class RestPricesController {

	@Autowired
	private PriceService priceService;

	@Autowired
	protected PriceDtoMapper priceMapper;

	
	/**
	 * Returns a price list according to the input filter
	 * @param date price date
	 * @param product product identifier
	 * @param brand brand identifier
	 * @return List of prices found
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<PriceDto> findAll(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date date,
			@RequestParam int product, @RequestParam int brand) {
		log.info("Recibida petición en findAll con fecha {}, product {} y brand {}", date, product, brand);
		return priceMapper.pricesToPriceDtos(priceService.findAll(date, product, brand));
	}

	/**
	 * Returns a single price according to the input filter and priority
	 * @param date price date
	 * @param product product identifier
	 * @param brand brand identifier
	 * @return Price found with higher priority
	 */
	@GetMapping("/one")
	@ResponseStatus(HttpStatus.OK)
	public PriceDto findOne(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date date,
			@RequestParam int product, @RequestParam int brand) {
		log.info("Recibida petición en findOne con fecha {}, product {} y brand {}", date, product, brand);
		return priceMapper.priceToPriceDto(priceService.findOne(date, product, brand));
	}
}
