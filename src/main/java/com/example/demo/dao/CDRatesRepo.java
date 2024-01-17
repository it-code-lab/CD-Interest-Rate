package com.example.demo.dao;

import com.example.demo.model.CDRates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRatesRepo extends CrudRepository<CDRates, String> {

    @Query("SELECT new com.example.demo.model.CDRates(c.cdUniqueId, c.termLength, c.interestRate, c.APY, c.minimumDeposit, c.maximumDeposit, c.penaltyDays, c.penaltyPercentage, c.cdType, c.status, c.startDate, c.endDate, c.createdBy, c.updatedBy) FROM CDRates c ")

    CDRates findRatesExcludingManagerRate();

}
