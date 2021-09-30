package com.example.testjava.ports.out;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.testjava.domain.Price;

/**
 * Port out interfaces for persisting data
 * @author pmarti14
 *
 */
public interface PriceRepository {

	/**
	 * Returns all prices found due entry params
	 * 
	 * @param date filter date
	 * @param product product id
	 * @param brand brand id
	 * @return list of prices
	 */
	public List<Price> findAll(Date date, Integer productId, Integer brandId);

	/**
	 * Return first price found due entry params and priority
	 * 
	 * @param date filter date
	 * @param product product id
	 * @param brand brand id
	 * @return first price by priority
	 */
	public Optional<Price> findOne(Date date, Integer productId, Integer brandId);

}
