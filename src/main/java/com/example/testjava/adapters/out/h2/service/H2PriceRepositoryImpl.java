package com.example.testjava.adapters.out.h2.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.testjava.adapters.out.h2.mapper.H2PriceEntityMapper;
import com.example.testjava.adapters.out.h2.repository.H2Repository;
import com.example.testjava.domain.Price;
import com.example.testjava.ports.out.PriceRepository;

/**
 * Repository for commons methods in H2 that implements port
 * 
 * @author pmarti14
 *
 */
@Repository
@Qualifier("h2")
public class H2PriceRepositoryImpl implements PriceRepository {

	@Autowired
	H2Repository h2Repository;

	@Autowired
	H2PriceEntityMapper priceMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Price> findAll(Date date, Integer productId, Integer brandId) {
		return priceMapper.pricesToPriceDomain(h2Repository.findAll(date, productId, brandId,
				PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "priceList"))));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Price> findOne(Date date, Integer productId, Integer brandId) {
		return priceMapper
				.pricesToPriceDomain(h2Repository.findAll(date, productId, brandId,
						PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"))))
				.stream()
				.findFirst();
	}

}
