package com.firma.service;

import com.firma.types.Presek;
import com.firma.types.ZahtevZaIzvod;
import org.springframework.stereotype.Service;

/**
 * Created by predrag on 6/28/17.
 */

public interface BankStatementService {

    Presek sendStatementRequest(ZahtevZaIzvod zahtev);
    com.firma.model.Presek saveBankStatement(com.firma.model.Presek presek);
}
