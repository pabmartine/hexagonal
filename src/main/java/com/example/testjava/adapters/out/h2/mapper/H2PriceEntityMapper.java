package com.example.testjava.adapters.out.h2.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.testjava.adapters.out.h2.entities.H2PriceEntity;
import com.example.testjava.domain.Price;

/**
 * Mapping interface from entity to domain
 * @author pmarti14
 *
 */
@Mapper(componentModel = "spring")
public interface H2PriceEntityMapper {

	/**
	 * Transforms a entity object into a domain
	 * @param price the entity
	 * @return the domain
	 */
	Price priceToPriceDomain(H2PriceEntity price);

	/**
	 * Transforms a list of entities objects into a list of domains
	 * @param prices the entity
	 * @return the domain
	 */
	List<Price> pricesToPriceDomain(List<H2PriceEntity> prices);
}