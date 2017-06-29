package com.banka.services;

import com.banka.model.Bank;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface BankService {

    Bank getOne(Long id);
    Bank save(Bank bank);
    Bank findByAccountCode(String accountCode);
    List<Bank> findAll();
    Bank findByLocal(boolean local);

}
