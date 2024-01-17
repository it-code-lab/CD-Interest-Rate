package com.example.demo.dao;

import com.example.demo.model.CDRates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CDRatesRepo extends CrudRepository<CDRates, String> {

    @Query("SELECT new com.example.demo.model.CDRates(c.cdUniqueId, c.termLength, c.interestRate, c.APY, c.stateCode, c.minimumDeposit, c.maximumDeposit, c.penaltyDays, c.penaltyPercentage, c.cdType, c.status, c.startDate, c.endDate, c.createdBy, c.updatedBy) FROM CDRates c where c.stateCode = :stateCode")
    List<CDRates> findRatesExcludingManagerRate(@Param("stateCode") String stateCode);

    @Query("SELECT c FROM CDRates c WHERE c.stateCode = :stateCode")
    List<CDRates> findAllByStateCode(@Param("stateCode") String stateCode);

}
