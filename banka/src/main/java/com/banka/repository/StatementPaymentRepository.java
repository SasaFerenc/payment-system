package com.banka.repository;

import com.banka.model.StatementPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatementPaymentRepository extends JpaRepository<StatementPayment, Long> {

    StatementPayment save(StatementPayment statementPayment);

    @Query(value = "SELECT s FROM StatementPayment s WHERE (s.creditorAccountNumber= ?1 OR s.debtorAccountNumber = ?1) AND s.paymentDate = ?2")
    List<StatementPayment> getByAccountNumberAndDate(String accountNumber, Date date);
}
