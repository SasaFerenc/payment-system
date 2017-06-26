package com.centralnabanka.ws.endpoint;

import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RtgsRequestEndpoint {

    private static final String NAMESPACE_URI = "http://www.paymentsystem.rs/schemas/mt103";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "mt103")
    @ResponsePayload
    public Mt900 handleMt103Request(@RequestPayload Mt103 request) {

        ObjectFactory factory = new ObjectFactory();

        return factory.createMt900();
    }
}
