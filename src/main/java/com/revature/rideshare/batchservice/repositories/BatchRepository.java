package com.revature.rideshare.batchservice.repositories;

import com.revature.rideshare.batchservice.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BatchRepository which extends the JpaRepository.
 * This repository handles our various queries.
 *
 * @author Adonis Cabreja
 *
 */

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

	/**
	 * Custom query that uses the @Query annotation to select a batch by location.
	 *
	 * @param location represents the batch's location.
	 * @return Check {@link com.revature.rideshare.batchservice.services.impl.BatchServiceImpl}
	 */

	@Query("select b from Batch b where b.batchLocation = ?1")
	public List<Batch> getBatchByLocation(String location);
}
