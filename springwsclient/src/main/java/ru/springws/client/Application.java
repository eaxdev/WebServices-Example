package ru.springws.client;

import countries.wsdl.GetCountryResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String countryDefault = "Poland";
            GetCountryResponse response = countryClient.getCountryInfo(countryDefault);
            System.out.println(response.getCountry());
        };
    }
}
