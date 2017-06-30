package com.banka.services;

import com.banka.types.Presek;
import com.banka.types.ZahtevZaIzvod;

public interface BankStatementService {

    Presek processBankStatementRequest(ZahtevZaIzvod request) throws Exception;
}
