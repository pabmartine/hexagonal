package com.example.testjava.adapters.out.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.testjava.adapters.out.mongo.entities.MongoDBPriceEntity;

/**
 * Repository for MongoDB that implements internal repo interface
 * @author pmarti14
 *
 */
@Repository
public class MongoDBRepositoryImpl implements MongoDBRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 *{@inheritDoc}
	 */
	@Override
	public List<MongoDBPriceEntity> findCustom(Date date, Integer productId, Integer brandId, Pageable pageable) {

		Query query = new Query();

		query.addCriteria(Criteria.where("startDate")
				.lt(date)
				.and("endDate")
				.gt(date)
				.and("productId")
				.is(productId)
				.and("brandId")
				.is(brandId));

		return mongoTemplate.find(query, MongoDBPriceEntity.class);
	}

}
