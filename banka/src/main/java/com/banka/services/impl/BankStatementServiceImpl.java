package com.banka.services.impl;

import com.banka.services.BankStatementService;
import com.banka.types.Presek;
import com.banka.types.ZaglavljePreseka;
import com.banka.types.ZahtevZaIzvod;
import org.springframework.stereotype.Service;

@Service
public class BankStatementServiceImpl implements BankStatementService {

    @Override
    public Presek processBankStatementRequest(ZahtevZaIzvod request) {
        Presek page = new Presek();

        ZaglavljePreseka header = new ZaglavljePreseka();
        header.setBrojPreseka(42);

        page.setZaglavljePreseka(header);
        return page;
    }
}
