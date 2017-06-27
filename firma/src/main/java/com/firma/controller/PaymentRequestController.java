package com.firma.controller;

import com.firma.model.Nalog;
import com.firma.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Predrag on 6/26/17.
 */

@Controller
@RequestMapping(value = "payments")
public class PaymentRequestController {

    private PaymentService paymentService;

    @Autowired
    public PaymentRequestController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/xml",
            produces = "application/json"
    )
    public Nalog createPayment(Nalog nalog){

        return paymentService.createPayment(nalog);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Nalog getPayment(@PathVariable String id){

        return paymentService.getPaymentById(Long.parseLong(id));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Nalog> getAllPayment(){
        return paymentService.getAllPayments();
    }

    @RequestMapping(
            value = "/send",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public void sendPayment(Nalog nalog){
        //TODO: transform nalog to generated nalog
        //paymentService.send();
    }
}
