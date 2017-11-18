package ru.springws.client;

import countries.wsdl.GetCountryRequest;
import countries.wsdl.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountryInfo(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);
        return (GetCountryResponse) getWebServiceTemplate()
                                    .marshalSendAndReceive("http://localhost:8080/ws", request);
    }

}
