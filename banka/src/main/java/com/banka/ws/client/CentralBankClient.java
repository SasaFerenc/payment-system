package com.banka.ws.client;

import com.banka.types.Mt103;
import com.banka.types.Mt900;
import com.banka.types.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.annotation.PostConstruct;

@Component
public class CentralBankClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CentralBankClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @PostConstruct
    public void init() {
        Mt900 res = sendMT103("ooo");
        System.out.println(res.getIdPoruke());
    }

    public Mt900 sendMT103(String id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Mt103 mt103 = objectFactory.createMt103();

        mt103.setIdPoruke(id);

        Mt900 response = (Mt900) webServiceTemplate.marshalSendAndReceive(mt103);
        return response;
    }

}
