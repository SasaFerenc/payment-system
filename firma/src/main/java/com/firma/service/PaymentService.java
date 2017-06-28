package com.firma.service;

import com.firma.model.Nalog;

import java.nio.file.NotLinkException;
import java.util.List;

public interface PaymentService {

    Nalog createPayment(Nalog nalog);

    Nalog getPaymentById(Long id);

    List<Nalog> getAllPayments();

    void send(com.firma.types.Nalog nalog);

}
