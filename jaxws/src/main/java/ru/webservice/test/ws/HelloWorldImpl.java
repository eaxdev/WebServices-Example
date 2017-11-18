package ru.webservice.test.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.webservice.test.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}
