package com.firma.service.implementation;

import com.firma.types.ZahtevZaIzvod;
import com.firma.service.BankStatementService;
import com.firma.ws.client.FirmClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by predrag on 6/28/17.
 */

@Service
public class BankStatementServiceImpl implements BankStatementService {

    private FirmClient firmClient;

    @Autowired
    public BankStatementServiceImpl(FirmClient firmClient){
        this.firmClient = firmClient;
    }

    @Override
    public void sendStatementRequest(ZahtevZaIzvod zahtev) {

        firmClient.getBankStatement(zahtev);
    }
}
