package com.example.testjava.adapters.out.mongo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.testjava.adapters.out.mongo.entities.MongoDBPriceEntity;
import com.example.testjava.domain.Price;

/**
 * Mapping interface from entity to domain
 * 
 * @author pmarti14
 *
 */
@Mapper(componentModel = "spring")
public interface MongoDBPriceEntityMapper {

	/**
	 * Transforms a entity object into a domain
	 * 
	 * @param price the entity
	 * @return the domain
	 */

	Price priceToPriceDto(MongoDBPriceEntity price);

	/**
	 * Transforms a list of entities objects into a list of domains
	 * 
	 * @param prices the entity
	 * @return the domain
	 */
	List<Price> pricesToPriceDtos(List<MongoDBPriceEntity> prices);
}