package com.banka.repository;

import com.banka.model.StatementPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementPaymentRepository extends JpaRepository<StatementPayment, Long> {

    StatementPayment getOne(Long id);
    List<StatementPayment> findAll();
    StatementPayment save(StatementPayment statementPayment);

}
