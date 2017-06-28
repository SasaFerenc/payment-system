package com.firma.service.implementation;

import com.firma.model.Nalog;
import com.firma.repository.PaymentRepository;
import com.firma.service.PaymentService;
import com.firma.ws.client.FirmClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private FirmClient firmClient;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, FirmClient firmClient){
        this.paymentRepository = paymentRepository;
        this.firmClient = firmClient;
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
        firmClient.sendPayment(nalog);
    }

}
