package com.banka.services.impl;

import com.banka.model.Bank;
import com.banka.repository.BankRepository;
import com.banka.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Override
    public Bank getOne(Long id) {
        return bankRepository.getOne(id);
    }

    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Bank findByAccountCode(String accountCode) {
        return bankRepository.findByAccountCode(accountCode);
    }
}
