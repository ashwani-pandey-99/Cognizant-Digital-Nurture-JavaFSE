package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("SpringLearnApplication main() started");
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication app = applicationContext.getBean(SpringLearnApplication.class);
        app.displayDate();
        app.displayCountry();
        app.displayPrototypeCountry();
        app.displayCountries();

        applicationContext.close();
        LOGGER.info("SpringLearnApplication main() completed");
    }

    public void displayDate() throws Exception {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml")) {
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed Date : {}", date);
        }
        LOGGER.info("END");
    }

    public void displayCountry() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = context.getBean("country", Country.class);
            Country anotherCountry = context.getBean("country", Country.class);
            LOGGER.debug("Country : {}", country);
            LOGGER.debug("Singleton scope check (same instance): {}", country == anotherCountry);
        }
        LOGGER.info("END");
    }

    public void displayPrototypeCountry() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = context.getBean("countryPrototype", Country.class);
            Country anotherCountry = context.getBean("countryPrototype", Country.class);
            LOGGER.debug("Prototype Country 1 : {}", country);
            LOGGER.debug("Prototype Country 2 : {}", anotherCountry);
            LOGGER.debug("Prototype scope check (same instance): {}", country == anotherCountry);
        }
        LOGGER.info("END");
    }

    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            ApplicationContext applicationContext = context;
            List<Country> countries = applicationContext.getBean("countryList", List.class);
            LOGGER.debug("Countries : {}", countries);
        }
        LOGGER.info("END");
    }
}
