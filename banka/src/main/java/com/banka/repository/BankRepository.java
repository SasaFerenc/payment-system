package com.banka.repository;

import com.banka.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank getOne(Long id);
    Bank save(Bank bank);
    @Query("select b from Bank b where b.accountNumber like ?1%")
    Bank findByAccountCode(String accountNumber);
    List<Bank> findAll();
    Bank findByLocal(boolean local);

}
