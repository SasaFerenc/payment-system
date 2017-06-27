package com.firma.service;

import com.firma.types.ZahtevZaIzvod;

/**
 * Created by predrag on 6/28/17.
 */
public interface BankStatementService {

    void sendStatementRequest(ZahtevZaIzvod zahtev);
}
