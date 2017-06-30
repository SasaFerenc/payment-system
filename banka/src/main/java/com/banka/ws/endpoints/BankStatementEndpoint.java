package com.banka.ws.endpoints;

import com.banka.services.BankStatementService;
import com.banka.types.Presek;
import com.banka.types.ZahtevZaIzvod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BankStatementEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEndpoint.class);

    private static final String NAMESPACE_URI = "http://www.paymentsystem.rs/schemas/zahtev_za_izvod";

    @Autowired
    BankStatementService bankStatementService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "zahtev_za_izvod")
    @ResponsePayload
    public Presek handleBankStatementRequest(@RequestPayload ZahtevZaIzvod request) throws Exception {
        LOGGER.info("Statement request for account: " + request.getBrojRacuna());

        return bankStatementService.processBankStatementRequest(request);
    }
}
