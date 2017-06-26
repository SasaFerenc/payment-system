package com.firma.controllers;

import com.firma.models.Faktura;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Predrag on 6/25/17.
 */

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/xml",
            produces = "application/json"
    )
    public Faktura createInvoice(Faktura faktura){

        return null;
    }

    @RequestMapping(
            value = "/send",
            method = RequestMethod.POST,
            consumes = "application.xml"
    )
    public void sendInvoice(Faktura faktura){

    }

    @RequestMapping(
            value = "/show/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Faktura getInvoice(@PathVariable String id){

        return null;
    }

    @RequestMapping(
            value = "/show/received",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Faktura> getReceivedInvoices(){

        return null;
    }

    @RequestMapping(
            value = "/show/sent",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Faktura> getSentInvoices(){

        return null;
    }
}
