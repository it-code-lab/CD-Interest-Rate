package com.example.demo.dao;

import com.example.demo.model.CDHistoricalRates;
import com.example.demo.model.CDRates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CDHistoricalRatesRepo extends CrudRepository<CDHistoricalRates, String> {

    //@Query("SELECT new com.example.demo.model.CDHistoricalRates(c.cdUniqueId, c.termLength, c.interestRate, c.APY, c.minimumDeposit, c.maximumDeposit, c.penaltyDays, c.penaltyPercentage, c.cdType, c.status, c.startDate, c.endDate, c.createdBy, c.updatedBy) FROM CDHistoricalRates c ")
    //CDHistoricalRates findRatesExcludingManagerRate();

    @Query("SELECT new com.example.demo.model.CDHistoricalRates(c.cdUniqueId, c.termLength, c.interestRate, c.APY, c.stateCode, c.minimumDeposit, c.penaltyDays, c.penaltyPercentage, c.cdType, c.status, c.startDate, c.endDate, c.createdBy, c.updatedBy) FROM CDHistoricalRates c where c.stateCode = :stateCode ")
    List<CDHistoricalRates> findRatesExcludingManagerRate(@Param("stateCode") String stateCode);

    @Query("SELECT c FROM CDRates c WHERE c.stateCode = :stateCode")
    List<CDHistoricalRates> findAllByStateCode(@Param("stateCode") String stateCode);
}
