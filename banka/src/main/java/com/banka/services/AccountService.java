package com.banka.services;

import com.banka.model.Account;

import java.util.List;

public interface AccountService {

    Account getOne(Long id);
    Account save(Account account);
    void deleteById(Long id);
    List<Account> findByCountNumber(String number);

}
