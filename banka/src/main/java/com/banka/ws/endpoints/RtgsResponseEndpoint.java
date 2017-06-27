package com.banka.ws.endpoints;

import com.banka.types.Mt103;
import com.banka.types.StringResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RtgsResponseEndpoint {

    private static final String NAMESPACE_URI = "http://www.paymentsystem.rs/schemas/mt103";
    private static final Logger LOGGER = LoggerFactory.getLogger(RtgsResponseEndpoint.class);

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "mt103")
    @ResponsePayload
    public StringResponse handleRtgs(@RequestPayload Mt103 mt103) {
        LOGGER.info(mt103.getIdPoruke());
        StringResponse response = new StringResponse();
        response.setMessage("OK");

        return response;
    }
}
