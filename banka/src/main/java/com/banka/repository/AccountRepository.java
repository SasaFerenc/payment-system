package com.banka.repository;

import com.banka.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findOne(Long id);
    List<Account> findByFirm(Long id);
    Account save(Account account);
    void delete(Long id);

}
