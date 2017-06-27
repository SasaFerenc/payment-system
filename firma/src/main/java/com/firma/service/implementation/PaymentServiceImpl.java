package com.firma.service.implementation;

import com.firma.model.Nalog;
import com.firma.repository.PaymentRepository;
import com.firma.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Created by Predrag on 6/27/17.
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, WebServiceTemplate webServiceTemplate){
        this.paymentRepository = paymentRepository;
        this.webServiceTemplate = webServiceTemplate;
    }

    @Override
    public Nalog createPayment(Nalog nalog) {
        return paymentRepository.save(nalog);
    }

    @Override
    public Nalog getPaymentById(Long id) {
        Optional<Nalog> payment = paymentRepository.findById(id);
        if(payment.isPresent())
            return payment.get();

        return null;
    }

    @Override
    public List<Nalog> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void send(com.firma.types.Nalog nalog) {
        //TODO: find other bank's address
        webServiceTemplate.marshalSendAndReceive("", nalog);
    }

}
