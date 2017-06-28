package com.firma.ws.client;

import com.firma.types.Nalog;
import com.firma.types.Presek;
import com.firma.types.StringResponse;
import com.firma.types.ZahtevZaIzvod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FirmClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private Logger LOGGER = LoggerFactory.getLogger(FirmClient.class);

    public void sendPayment(Nalog nalog){
        StringResponse response = (StringResponse)webServiceTemplate.marshalSendAndReceive("http://192.168.1.3:8080/ws", nalog);
        LOGGER.info(response.getMessage());
    }

    public Presek getBankStatement(ZahtevZaIzvod zahtev){

        return(Presek)webServiceTemplate.marshalSendAndReceive("", zahtev);
    }
}
