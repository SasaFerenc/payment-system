package com.banka;

import com.banka.ws.client.CentralBankClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by novica on 27.6.17..
 */
@RestController
public class TempEndpoint {

    @Autowired
    CentralBankClient centralBankClient;

    @RequestMapping(method = RequestMethod.GET, value = "/posalji")
    public void getNesto() {
        centralBankClient.sendMT103("ooo");
    }
}
