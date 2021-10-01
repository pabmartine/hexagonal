package com.example.testjava.adapters.out.h2.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.testjava.adapters.out.h2.entities.H2PriceEntity;

/**
 * Repository for commons methods in H2
 * 
 * @author pmarti14
 *
 */
public interface H2Repository extends JpaRepository<H2PriceEntity, Integer> {

	/**
	 * Returns all prices found due entry params
	 * 
	 * @param date      filter date
	 * @param productId product id
	 * @param brandId   brand id
	 * @param pageable  page object from spring
	 * @return          list of prices
	 */
	@Query("SELECT p FROM H2PriceEntity p where (:date between p.startDate and p.endDate) and p.productId = :productId and p.brandId = :brandId")
	public List<H2PriceEntity> findAll(@Param("date") Date date, @Param("productId") Integer productId,
			@Param("brandId") Integer brandId, Pageable pageable);

}
