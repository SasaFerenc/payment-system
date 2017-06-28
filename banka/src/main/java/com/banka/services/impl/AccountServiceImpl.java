package com.banka.services.impl;

import com.banka.model.Account;
import com.banka.repository.AccountRepository;
import com.banka.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account getOne(Long id) {
        return accountRepository.getOne(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> findByCountNumber(String number) {
        return accountRepository.findByCountNumber(number);
    }
}
