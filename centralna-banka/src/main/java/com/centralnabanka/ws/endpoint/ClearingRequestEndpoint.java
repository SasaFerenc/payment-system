package com.centralnabanka.ws.endpoint;

import com.centralnabanka.service.ClearingService;
import com.centralnabanka.types.Mt102;
import com.centralnabanka.types.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClearingRequestEndpoint {

    private static final String NAMESPACE_URI = "http://www.paymentsystem.rs/schemas/mt102";

    @Autowired
    private ClearingService clearingService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "mt102")
    @ResponsePayload
    public StringResponse handleMt102Request(@RequestPayload Mt102 request) {
        clearingService.processMt102Request(request);

        StringResponse response = new StringResponse();
        response.setMessage("OK");

        return response;
    }
}
