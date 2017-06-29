package com.banka.ws.endpoints;

import com.banka.model.Account;
import com.banka.model.Bank;
import com.banka.model.PaymentRequest;
import com.banka.services.AccountService;
import com.banka.services.PaymentRequestService;
import com.banka.services.StatementConversionService;
import com.banka.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.List;

@Endpoint
public class ResponseEndpoint {

    private static final String NAMESPACE_URI_103 = "http://www.paymentsystem.rs/schemas/mt103";
    private static final String NAMESPACE_URI_102 = "http://www.paymentsystem.rs/schemas/mt102";
    private static final String NAMESPACE_URI_910 = "http://www.paymentsystem.rs/schemas/mt910";
    private static final String NAMESPACE_URI_900 = "http://www.paymentsystem.rs/schemas/mt900";
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEndpoint.class);

    @Autowired
    AccountService accountService;

    @Autowired
    PaymentRequestService paymentRequestService;

    @Autowired
    StatementConversionService statementConversionService;

    @PayloadRoot(namespace = NAMESPACE_URI_103, localPart = "mt103")
    @ResponsePayload
    public StringResponse handleRtgs103(@RequestPayload Mt103 mt103) {
        LOGGER.info("103 stigla: " + mt103.getIdPoruke());
        StringResponse response = new StringResponse();
        response.setMessage("OK");

        statementConversionService.saveStatementPaymentFrom103(mt103, false);

        Account account = accountService.findByCountNumber(mt103.getPodaciOPlacanju().getRacunPoverioca()).get(0);
        account.setTotal(account.getTotal().add(mt103.getPodaciOPlacanju().getIznos()));
        accountService.save(account);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_102, localPart = "mt102")
    @ResponsePayload
    public StringResponse handle102(@RequestPayload Mt102 mt102) {
        LOGGER.info("102 stigla: " + mt102.getIdPoruke());

        statementConversionService.saveStatementPaymentFrom102(mt102, false);

        for(Mt102.PojedinacnaPlacanja pojedinacnaPlacanja : mt102.getPojedinacnaPlacanja()) {
            Account account = accountService.findByCountNumber(pojedinacnaPlacanja.getPodaciOPlacanju().getRacunPoverioca()).get(0);
            account.setTotal(account.getTotal().add(pojedinacnaPlacanja.getPodaciOPlacanju().getIznos()));
            accountService.save(account);
        }

        StringResponse response = new StringResponse();
        response.setMessage("OK");

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_900, localPart = "mt900")
    @ResponsePayload
    public StringResponse handle900(@RequestPayload Mt900 mt900) {
        LOGGER.info("900 stigla: " + mt900.getIdPoruke());
        StringResponse response = new StringResponse();
        response.setMessage("OK");

        String mtID = mt900.getIdPoruke();
        List<PaymentRequest> requests = paymentRequestService.findByIdMT(mtID);
        for(PaymentRequest request : requests) {
            List<Account> accounts = accountService.findByCountNumber(request.getCreditorAccountNumber());
            for(Account account : accounts) {
                account.setTotal(account.getTotal().subtract(account.getReserved()));
                account.setReserved(new BigDecimal(0));
                accountService.save(account);
            }
        }

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
