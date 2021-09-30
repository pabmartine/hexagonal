package com.example.testjava.adapters.out.mongo.entities;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The price document
 * @author pmarti14
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prices")
public class MongoDBPriceEntity {

	@Id
	private String id;
	private Integer priceList;
	private Integer brandId;
	private Date startDate;
	private Date endDate;
	private Integer productId;
	private Integer priority;
	private Double price;
	private String curr;

}
