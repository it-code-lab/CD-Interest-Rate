package com.example.demo.controller;

import com.example.demo.dao.CDHistoricalRatesRepo;
import com.example.demo.dao.CDRatesRepo;
import com.example.demo.dao.CDRatesStatusRepo;
import com.example.demo.model.CDHistoricalRates;
import com.example.demo.model.CDRates;
import com.example.demo.service.ConversionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CDRatesController {

    @Autowired
    private CDRatesRepo rateRepo;

    @Autowired
    private CDRatesStatusRepo statusRepo;

    @Autowired
    private CDHistoricalRatesRepo historicalRatesRepo;

    @Autowired
    private ConversionUtility conversionUtility;

    @GetMapping("/consumer/currentrates/{zip}")
    private List<CDRates> getRatesForConsumer( @PathVariable String zip){
        String state = conversionUtility.getState(zip);
        return (List<CDRates>) rateRepo.findRatesExcludingManagerRate(state);
    }

    @GetMapping("/consumer/hisoricalrates/{zip}")
    private List<CDHistoricalRates> getHistoricalRatesForConsumer(@PathVariable String zip){
        String state = conversionUtility.getState(zip);
        return (List<CDHistoricalRates>) historicalRatesRepo.findRatesExcludingManagerRate(state);
    }

    @GetMapping("/manager/currentrates/{zip}")
    private List<CDRates> getRates(@PathVariable String zip){
        String state = conversionUtility.getState(zip);
        return (List<CDRates>) rateRepo.findAllByStateCode(state);
    }

    @PostMapping("/admin/currentrates")
    private CDRates addCurrentRates(@RequestBody CDRates newRate){
        return rateRepo.save(newRate);
    }

    @PutMapping("/admin/rate/{id}")
    private Optional<CDRates> updateRate(@RequestBody CDRates newRate, @PathVariable String id){

        return rateRepo.findById(id)
                .map(rate -> {
                    rate.setStatus(newRate.getStatus());
                    return rateRepo.save(rate);
                });
    }

    @PutMapping("/admin/archiverate/{id}")
    private Optional<CDRates> archiveRate(@RequestBody CDRates oldRate, @PathVariable String id){

         return rateRepo.findById(id)
                .map(rate -> {
                    rateRepo.delete(rate);
                    CDHistoricalRates historicalRate = new CDHistoricalRates();

                    historicalRate.setAPY(oldRate.getAPY());
                    historicalRate.setInterestRate(oldRate.getInterestRate());
                    historicalRate.setManagerRate(oldRate.getManagerRate());
                    historicalRate.setCdType(oldRate.getCdType());
                    historicalRate.setStatus(oldRate.getStatus());
                    historicalRate.setCdUniqueId(oldRate.getCdUniqueId());
                    historicalRate.setStatus(oldRate.getStatus());
                    historicalRate.setEndDate(oldRate.getEndDate());
                    historicalRate.setCreatedBy(oldRate.getCreatedBy());

                    historicalRatesRepo.save(historicalRate);
                    return null;
                });
    }
}
