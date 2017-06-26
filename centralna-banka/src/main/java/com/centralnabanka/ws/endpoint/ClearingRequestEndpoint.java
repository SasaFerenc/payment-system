package com.centralnabanka.ws.endpoint;

import com.centralnabanka.types.Mt102;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClearingRequestEndpoint {

    private static final String NAMESPACE_URI = "http://www.paymentsystem.rs/schemas/mt102";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "mt102")
    @ResponsePayload
    public Mt900 handleMt102Request(@RequestPayload Mt102 request) {

        ObjectFactory factory = new ObjectFactory();

        return factory.createMt900();
    }
}
