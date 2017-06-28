package com.banka.ws.client;

import com.banka.model.Account;
import com.banka.services.AccountService;
import com.banka.types.Mt103;
import com.banka.types.Mt900;
import com.banka.types.ObjectFactory;
import com.banka.types.PodaciOPlacanju;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class CentralBankClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CentralBankClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    AccountService accountService;

    public Mt900 sendMT103(Mt103 mt103) {
        Mt900 response = (Mt900) webServiceTemplate.marshalSendAndReceive(mt103);
        LOGGER.info("900 doslo: " + response.getIdPoruke());

        Account account = accountService.findByCountNumber(mt103.getPodaciOPlacanju().getRacunDuznika()).get(0);
        account.setReserved(account.getReserved().subtract(response.getIznos()));
        account.setTotal(account.getTotal().subtract(response.getIznos()));
        accountService.save(account);
        return response;
    }

}
