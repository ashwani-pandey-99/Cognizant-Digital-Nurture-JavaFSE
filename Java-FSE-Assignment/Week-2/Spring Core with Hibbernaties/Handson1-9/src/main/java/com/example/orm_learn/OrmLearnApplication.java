package com.example.orm_learn;

import com.example.orm_learn.model.Country;
import com.example.orm_learn.service.CountryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrmLearnApplication {

	public static void main(String[] args) throws Exception {

		ApplicationContext context =
				SpringApplication.run(OrmLearnApplication.class, args);

		CountryService service =
				context.getBean(CountryService.class);

		System.out.println("All Countries");
		System.out.println(service.getAllCountries());

		System.out.println();

		System.out.println("Find Country");
		System.out.println(service.findCountryByCode("IN"));

		Country country =
				new Country("NP", "Nepal");

		service.addCountry(country);

		service.updateCountry(
				"NP",
				"Federal Democratic Republic of Nepal");

		System.out.println(service.searchCountry("Ind"));

		service.deleteCountry("NP");

	}

}