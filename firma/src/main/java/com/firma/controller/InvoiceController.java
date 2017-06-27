package com.firma.controller;

import com.firma.service.implementation.InvoiceServiceImpl;
import com.firma.model.Faktura;
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

    private InvoiceServiceImpl invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService){
        this.invoiceService = invoiceService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public Faktura createInvoice(Faktura faktura){
        return invoiceService.save(faktura);
    }

    @RequestMapping(
            value = "/send",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public void sendInvoice(Faktura faktura){
        invoiceService.sentInvoice(faktura);
    }

    @RequestMapping(
            value = "/show/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Faktura getInvoice(@PathVariable String id){

        return invoiceService.getInvoiceById(Long.parseLong(id));
    }

    @RequestMapping(
            value = "/show/received",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Faktura> getReceivedInvoices(){

        return invoiceService.getReceived(true);
    }

    @RequestMapping(
            value = "/show/sent",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<Faktura> getSentInvoices(){

        return invoiceService.getSent(true);
    }
}
