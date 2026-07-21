package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    public Country getCountry(String code) {
        
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");
        
        List<Country> countryList =
                (List<Country>) context.getBean("countryList");
        
        for (Country country : countryList) {
            if (country.getCode().equalsIgnoreCase(code)) {
                ((ClassPathXmlApplicationContext) context).close();
                return country;
            }
        }
        
        ((ClassPathXmlApplicationContext) context).close();
        
        return null;
    }
}