package com.example.testjava.adapters.in.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.testjava.adapters.in.rest.dto.PriceDto;
import com.example.testjava.domain.Price;

/**
 * Mapping interface from domain to DTO
 * @author pmarti14
 *
 */
@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

	/**
	 * Transforms a domain object into a DTO
	 * @param price domain object
	 * @return dto
	 */
	@Mappings({
		@Mapping(target = "startDate", dateFormat = "dd/MM/yyyy HH:mm:ss"),
		@Mapping(target = "endDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
	})
	PriceDto priceToPriceDto(Price price);

	/**
	 * Transforms a list of domain objects into a list of DTOs
	 * @param prices the domain objects
	 * @return the dtos
	 */
	List<PriceDto> pricesToPriceDtos(List<Price> prices);
}