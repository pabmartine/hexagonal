package com.example.testjava.adapters.out.h2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The price entity
 * @author pmarti14
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class H2PriceEntity {

	@Id
	@Column(name = "PRICE_LIST", nullable = false)
	private Integer priceList;
	@Column(name = "BRAND_ID", nullable = false)
	private Integer brandId;
	@Column(name = "START_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name = "END_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(name = "PRODUCT_ID", nullable = false)
	private Integer productId;
	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;
	@Column(name = "PRICE", nullable = false)
	private Double price;
	@Column(name = "CURR", nullable = false)
	private String curr;

}
