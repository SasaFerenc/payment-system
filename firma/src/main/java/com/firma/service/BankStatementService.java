package com.firma.service;

import com.firma.types.Presek;
import com.firma.types.ZahtevZaIzvod;

public interface BankStatementService {

    Presek sendStatementRequest(ZahtevZaIzvod zahtev);
    com.firma.model.Presek saveBankStatement(com.firma.model.Presek presek);
}
