package com.centralnabanka.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class BankClient {

    private WebServiceTemplate webServiceTemplate;

    @Autowired
    public BankClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public void sendMessage(Object message, String bankUrl) {
        webServiceTemplate.marshalSendAndReceive(bankUrl, message);
    }

}
