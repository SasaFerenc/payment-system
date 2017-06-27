package com.banka.services;

import com.banka.model.Account;

import java.util.List;

public interface AccountService {

    Account findOne(Long id);
    List<Account> findByFirm(Long id);
    Account save(Account account);
    void delete(Long id);

}
