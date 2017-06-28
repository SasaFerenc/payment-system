package com.banka.services;

import com.banka.model.PaymentRequest;

public interface PaymentRequestService {

    PaymentRequest findOne(Long id);
    PaymentRequest save(PaymentRequest paymentRequest);

}
