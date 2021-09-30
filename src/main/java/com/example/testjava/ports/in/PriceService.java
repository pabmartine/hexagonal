package com.example.testjava.ports.in;

import java.util.Date;
import java.util.List;

import com.example.testjava.domain.Price;


/**
 * Port in interface
 * @author pmarti14
 *
 */
public interface PriceService {

	/**
	 * Returns all prices found due entry params
	 * 
	 * @param date filter date
	 * @param product product id
	 * @param brand brand id
	 * @return list of prices
	 */
	public List<Price> findAll(Date date, Integer product, Integer brand);

	/**
	 * Return first price found due entry params and priority
	 * 
	 * @param date filter date
	 * @param product product id
	 * @param brand brand id
	 * @return first price by priority
	 */
	public Price findOne(Date date, Integer product, Integer brand);

}
