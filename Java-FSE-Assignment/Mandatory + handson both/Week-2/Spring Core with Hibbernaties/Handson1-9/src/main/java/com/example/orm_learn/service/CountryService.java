package com.example.orm_learn.service;

import com.example.orm_learn.exception.CountryNotFoundException;
import com.example.orm_learn.model.Country;
import com.example.orm_learn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String code)
            throws CountryNotFoundException {

        return countryRepository.findById(code)
                .orElseThrow(() ->
                        new CountryNotFoundException("Country Not Found"));

    }

    @Transactional
    public void addCountry(Country country) {

        countryRepository.save(country);

    }

    @Transactional
    public void updateCountry(String code, String name)
            throws CountryNotFoundException {

        Country country = findCountryByCode(code);

        country.setName(name);

        countryRepository.save(country);

    }

    @Transactional
    public void deleteCountry(String code) {

        countryRepository.deleteById(code);

    }

    @Transactional(readOnly = true)
    public List<Country> searchCountry(String name) {

        return countryRepository.findByNameContainingIgnoreCase(name);

    }

}