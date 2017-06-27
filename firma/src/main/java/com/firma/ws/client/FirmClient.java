package com.firma.ws.client;

import com.firma.types.Nalog;
import com.firma.types.Presek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Predrag on 6/26/17.
 */

@Component
public class FirmClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void sendPayment(Nalog nalog){

    }

    public Presek getBankStatement(){

        return null;
    }
}
