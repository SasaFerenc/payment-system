package com.banka.services.impl;

import com.banka.model.StatementPayment;
import com.banka.repository.StatementPaymentRepository;
import com.banka.services.StatementPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatementPaymentServiceImpl implements StatementPaymentService {

    @Autowired
    StatementPaymentRepository statementPaymentRepository;

    @Override
    public StatementPayment getOne(Long id) {
        return statementPaymentRepository.getOne(id);
    }

    @Override
    public List<StatementPayment> findAll() {
        return statementPaymentRepository.findAll();
    }

    @Override
    public StatementPayment save(StatementPayment statementPayment) {
        return statementPaymentRepository.save(statementPayment);
    }
}
