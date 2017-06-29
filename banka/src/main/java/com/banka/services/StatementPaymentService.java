package com.banka.services;


import com.banka.model.StatementPayment;

import java.util.List;

public interface StatementPaymentService {

    StatementPayment getOne(Long id);
    List<StatementPayment> findAll();
    StatementPayment save(StatementPayment statementPayment);

}
