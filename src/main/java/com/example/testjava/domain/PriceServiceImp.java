package com.example.testjava.domain;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.testjava.ports.in.PriceService;
import com.example.testjava.ports.out.PriceRepository;

/**
 * Use cases implementations
 * 
 * @author pmarti14
 *
 */
@Service
public class PriceServiceImp implements PriceService {

	@Qualifier("h2")
//	@Qualifier("mongo")
	@Autowired
	PriceRepository priceRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Price> findAll(Date date, Integer product, Integer brand) {
		return priceRepository.findAll(date, product, brand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price findOne(Date date, Integer product, Integer brand) {
		return priceRepository.findOne(date, product, brand)
				.orElse(null);
	}

}
