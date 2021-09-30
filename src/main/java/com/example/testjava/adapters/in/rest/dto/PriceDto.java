package com.example.testjava.adapters.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Object in charge of mapping the domain class and returning it abroad
 * @author pmarti14
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

	private Integer priceList;
	private Integer brandId;
	private String startDate;
	private String endDate;
	private Integer productId;
	private Double price;
	private String curr;
}
