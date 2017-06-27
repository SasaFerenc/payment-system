package com.firma.service;

import com.firma.model.Nalog;

import java.util.List;

/**
 * Created by Predrag on 6/27/17.
 */
public interface PaymentService {

    Nalog createPayment(Nalog nalog);

    Nalog getPaymentById(Long id);

    List<Nalog> getAllPayments();

    void send(com.firma.types.Nalog nalog);

}
