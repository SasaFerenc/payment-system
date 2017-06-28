package com.banka.services;

import com.banka.model.Account;
import com.banka.types.Nalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountService accountService;

    public void handleTransaction(Nalog nalog) {
        List<Account> accountsByNumber = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca());
        if(accountsByNumber.size() > 0) {
            transactionOnTheSameBank(nalog);
        }
    }

    public void transactionOnTheSameBank(Nalog nalog) {
        Account creditorAccount = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunDuznika()).get(0);
        Account debtorAccount = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca()).get(0);
        creditorAccount.setTotal(creditorAccount.getTotal().subtract(nalog.getPodaciOPlacanju().getIznos()));
        creditorAccount.setTotal(debtorAccount.getTotal().add(nalog.getPodaciOPlacanju().getIznos()));

        accountService.save(creditorAccount);
        accountService.save(debtorAccount);
    }

}
