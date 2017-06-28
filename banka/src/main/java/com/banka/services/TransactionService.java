package com.banka.services;

import com.banka.model.Account;
import com.banka.repository.AccountRepository;
import com.banka.types.Nalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountRepository accountRepository;

    public void handleTransaction(Nalog nalog) {
        List<Account> accountsByNumber = accountRepository.findByCountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca());
        if(accountsByNumber.size() > 0) {
            transactionOnTheSameBank(nalog);
        }
    }

    public void transactionOnTheSameBank(Nalog nalog) {
        Account creditorAccount = accountRepository.findByCountNumber(nalog.getPodaciOPlacanju().getRacunDuznika()).get(0);
        Account debtorAccount = accountRepository.findByCountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca()).get(0);
        creditorAccount.setTotal(creditorAccount.getTotal().subtract(nalog.getPodaciOPlacanju().getIznos()));
        debtorAccount.setTotal(debtorAccount.getTotal().add(nalog.getPodaciOPlacanju().getIznos()));

        accountRepository.save(creditorAccount);
        accountRepository.save(debtorAccount);
    }

}
