package com.centralnabanka.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class BankClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankClient.class);

    private WebServiceTemplate webServiceTemplate;

    @Autowired
    public BankClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public void sendMessage(Object message, String bankUrl) {
        LOGGER.info("Sending " + message.getClass().getSimpleName() + " message to " + bankUrl + " ...");

        webServiceTemplate.marshalSendAndReceive(bankUrl + "/ws", message);
    }

}
