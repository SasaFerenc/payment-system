package com.banka.services.impl;

import com.banka.model.PaymentRequest;
import com.banka.repository.PaymentRequestRepository;
import com.banka.services.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PaymentRequestServiceImpl implements PaymentRequestService {

    @Autowired
    PaymentRequestRepository paymentRequestRepository;

    @Override
    public PaymentRequest findOne(Long id) {
        return paymentRequestRepository.getOne(id);
    }

    @Override
    public List<PaymentRequest> findByBankCode(String bankCode) {
        return paymentRequestRepository.findByBankCode(bankCode);
    }

    @Override
    public PaymentRequest save(PaymentRequest paymentRequest) {
        return paymentRequestRepository.save(paymentRequest);
    }
}
