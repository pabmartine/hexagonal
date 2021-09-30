package com.example.testjava.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain class
 * @author pmarti14
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

	private Integer priceList;
	private Integer brandId;
	private Date startDate;
	private Date endDate;
	private Integer productId;
	private Integer priority;
	private Double price;
	private String curr;

}
