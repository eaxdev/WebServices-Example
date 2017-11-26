package ru.springws.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryConfigTest {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("countries.wsdl");
        return jaxb2Marshaller;
    }

    @Bean
    public CountryClient countryClientTest(Jaxb2Marshaller marshaller) {
        CountryClient countryClient = new CountryClient();
        countryClient.setDefaultUri("http://localhost:8080/ws");
        countryClient.setMarshaller(marshaller);
        countryClient.setUnmarshaller(marshaller);
        return countryClient;
    }
}
