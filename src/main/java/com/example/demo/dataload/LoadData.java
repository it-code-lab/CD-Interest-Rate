package com.example.demo.dataload;

import com.example.demo.dao.CDRatesRepo;
import com.example.demo.dao.CDRatesStatusRepo;
import com.example.demo.model.CDRates;
import com.example.demo.model.CDRatesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class LoadData implements ApplicationRunner {

    @Autowired
    CDRatesRepo rateRepo;

    @Autowired
    CDRatesStatusRepo rateStatusRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rateRepo.save(new CDRates("20231201m12S10kCA","M1",4.5,4.7,4.6, 1000,100000,10, 5, "S","Y",parseDate("2024-01-15"), parseDate("2024-03-31"),"TestUser", "AdminUser"));
        rateStatusRepo.save(new CDRatesStatus("20231201m12S10kCA",12000, 1000000,12, 1000,  parseDate("2024-01-15"), parseDate("2024-03-31"), "TestUser", "AdminUser"));
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
