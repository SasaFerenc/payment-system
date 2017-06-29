package com.banka.services;

import com.banka.model.PaymentRequest;

import java.util.List;

public interface PaymentRequestService {

    PaymentRequest findOne(Long id);
    PaymentRequest save(PaymentRequest paymentRequest);
    List<PaymentRequest> findByBankCode(String bankCode);

}
