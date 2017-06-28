package com.firma.service.implementation;

import com.firma.repository.BankStatementRepository;
import com.firma.types.Presek;
import com.firma.types.ZahtevZaIzvod;
import com.firma.service.BankStatementService;
import com.firma.ws.client.FirmClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankStatementServiceImpl implements BankStatementService {

    private FirmClient firmClient;
    private BankStatementRepository bankRepository;

    @Autowired
    public BankStatementServiceImpl(FirmClient firmClient, BankStatementRepository bankRepository){
        this.firmClient = firmClient;
        this.bankRepository = bankRepository;
    }

    @Override
    public Presek sendStatementRequest(ZahtevZaIzvod zahtev) {
        return firmClient.getBankStatement(zahtev);
    }

    @Override
    public com.firma.model.Presek saveBankStatement(com.firma.model.Presek presek) {
        return bankRepository.save(presek);
    }
}
