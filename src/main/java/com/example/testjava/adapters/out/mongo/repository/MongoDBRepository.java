package com.example.testjava.adapters.out.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.testjava.adapters.out.mongo.entities.MongoDBPriceEntity;

/**
 * Repository for MongoDB
 * 
 * @author pmarti14
 *
 */
public interface MongoDBRepository {

	/**
	 * Returns all prices found due entry params
	 * 
	 * @param date      filter date
	 * @param productId product id
	 * @param brandId   brand id
	 * @param pageable  page object from spring
	 * @return list of prices
	 */
	public List<MongoDBPriceEntity> findCustom(@Param("date") Date date, @Param("productId") Integer productId,
			@Param("brandId") Integer brandId, Pageable pageable);

}
