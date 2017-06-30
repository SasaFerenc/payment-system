package com.banka.services;

import com.banka.model.Bank;
import java.util.List;

public interface BankService {

    Bank getOne(Long id);
    Bank save(Bank bank);
    Bank findByAccountCode(String accountCode);
    List<Bank> findAll();
    Bank findByLocal(boolean local);
}
