package ru.springws.client;

import countries.wsdl.GetCountryResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = CountryConfig.class)
public class CountryClientTest {

    @Autowired
    private CountryClient countryClient;

    private MockWebServiceServer mockWebServiceServer;

    @Before
    public void createServer() {
        mockWebServiceServer = MockWebServiceServer.createServer(countryClient);
    }


    @Test
    public void getCustomerCount() throws Exception {
        Source expectedRequestPayload =
                new StringSource("<gs:getCountryRequest xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\"><gs:name>Poland</gs:name></gs:getCountryRequest>");
        Source responsePayload = new StringSource("<ns2:getCountryResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\">" +
                "<ns2:country>" +
                "            <ns2:name>Poland_dsadadasdasdasds</ns2:name>" +
                "            <ns2:population>38186860</ns2:population>" +
                "            <ns2:capital>Warsaw</ns2:capital>" +
                "            <ns2:currency>PLN</ns2:currency>" +
                "</ns2:country>" +
                "</ns2:getCountryResponse>");

        mockWebServiceServer.expect(payload(expectedRequestPayload)).andRespond(withPayload(responsePayload));

        // client.getCustomerCount() uses the WebServiceTemplate
        Map<String, String> namespaces = Collections.singletonMap("gs", "http://spring.io/guides/gs-producing-web-service");
       mockWebServiceServer.expect(RequestMatchers.xpath("/gs:getCountryRequest/gs:name", namespaces).exists())
                .andRespond(withPayload(responsePayload));



        //System.out.println(response.getCountry());
        // assertEquals(10, response.getCustomerCount());


        GetCountryResponse response = countryClient.getCountryInfo("Poland");
        assertEquals("Warsaw", response.getCountry().getCapital());
        countryClient.getCountryInfo("Poland");

        mockWebServiceServer.verify();
    }
}
