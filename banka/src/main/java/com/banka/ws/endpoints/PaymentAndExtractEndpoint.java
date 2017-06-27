package com.banka.ws.endpoints;

import com.banka.types.Nalog;
import com.banka.types.StringResponse;
import com.banka.types.ZahtevZaIzvod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PaymentAndExtractEndpoint {

    private static final String NAMESPACE_URI = "http://www.paymentsystem.rs/schemas/nalog_za_placanje";
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAndExtractEndpoint.class);

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "nalog")
    @ResponsePayload
    public StringResponse handlePaymentRequest(@RequestPayload Nalog nalog) {
        LOGGER.info("Nalog za uplatu: " + nalog.getIdPoruke());
        StringResponse response = new StringResponse();
        response.setMessage("OK");

        return response;
    }

    /*@PayloadRoot(namespace = NAMESPACE_URI, localPart = "izvod")
    public StringResponse handleExtractRequest(@RequestPayload ZahtevZaIzvod zahtevZaIzvod) {
        LOGGER.info("zahtjev za izvod");

        StringResponse response = new StringResponse();
        response.setMessage("OK");
        return response;
    }*/

}
