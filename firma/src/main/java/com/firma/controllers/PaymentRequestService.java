package com.firma.controllers;

import com.firma.models.Nalog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Predrag on 6/26/17.
 */
@RequestMapping(value = "payments")
public class PaymentRequestService {

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/xml",
            produces = "application/json"
    )
    public Nalog createPayment(){

        return null;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Nalog getPayment(@PathVariable String id){

        return null;
    }
}
