package com.banka.services.impl;

import com.banka.model.Account;
import com.banka.repository.AccountRepository;
import com.banka.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findOne(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public List<Account> findByFirm(Long id) {
        return accountRepository.findByFirm(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findByCountNumber(String number) {
        return accountRepository.findByCountNumber(number);
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(id);
    }
}
