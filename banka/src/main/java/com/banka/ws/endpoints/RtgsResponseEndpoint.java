package com.banka.ws.endpoints;

import com.banka.model.Account;
import com.banka.services.AccountService;
import com.banka.types.Mt103;
import com.banka.types.Mt910;
import com.banka.types.StringResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RtgsResponseEndpoint {

    private static final String NAMESPACE_URI_103 = "http://www.paymentsystem.rs/schemas/mt103";
    private static final String NAMESPACE_URI_910 = "http://www.paymentsystem.rs/schemas/mt910";
    private static final Logger LOGGER = LoggerFactory.getLogger(RtgsResponseEndpoint.class);

    @Autowired
    AccountService accountService;

    @PayloadRoot(namespace = NAMESPACE_URI_103, localPart = "mt103")
    @ResponsePayload
    public StringResponse handleRtgs103(@RequestPayload Mt103 mt103) {
        LOGGER.info("103 stigla: " + mt103.getIdPoruke());
        StringResponse response = new StringResponse();
        response.setMessage("OK");

        Account account = accountService.findByCountNumber(mt103.getPodaciOPlacanju().getRacunPoverioca()).get(0);
        account.setTotal(account.getTotal().add(mt103.getPodaciOPlacanju().getIznos()));
        accountService.save(account);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_910, localPart = "mt910")
    @ResponsePayload
    public StringResponse handleRtgs910(@RequestPayload  Mt910 mt910) {
        LOGGER.info("910 stigla: " + mt910.getIdPoruke());
        StringResponse response = new StringResponse();
        response.setMessage("OK");

        return response;
    }
}
