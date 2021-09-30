package com.example.testjava.adapters.out.mongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.testjava.adapters.out.mongo.mapper.MongoDBPriceEntityMapper;
import com.example.testjava.adapters.out.mongo.repository.MongoDBRepository;
import com.example.testjava.domain.Price;
import com.example.testjava.ports.out.PriceRepository;

/**
 * Repository for MongoDB that implements port
 * 
 * @author pmarti14
 *
 */
@Component
@Qualifier("mongo")
public class MongoDBPriceRepositoryImpl implements PriceRepository {

	@Autowired
	MongoDBRepository mongoDBRepository;

	@Autowired
	MongoDBPriceEntityMapper priceMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Price> findAll(Date date, Integer productId, Integer brandId) {
		return priceMapper.pricesToPriceDtos(mongoDBRepository.findCustom(date, productId, brandId,
				PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "priceList"))));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Price> findOne(Date date, Integer productId, Integer brandId) {
		return priceMapper
				.pricesToPriceDtos(mongoDBRepository.findCustom(date, productId, brandId,
						PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"))))
				.stream()
				.findFirst();
	}

}
