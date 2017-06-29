package com.firma.controller;

import com.firma.model.Firma;
import com.firma.repository.FirmRepository;
import com.firma.service.InvoiceService;
import com.firma.model.Faktura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private static Logger loger = Logger.getLogger("S");
    private InvoiceService invoiceService;
    private FirmRepository firmRepository;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, FirmRepository firmRepository){

        this.firmRepository = firmRepository;
        this.invoiceService = invoiceService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )

    @ResponseBody
    public Faktura createInvoice(@RequestBody Faktura faktura){
        faktura.setForeignKey();
        faktura.setSent(false);
        faktura.setReceived(false);
        return invoiceService.save(faktura);
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public List<Faktura> getAllInvoices(){
        return invoiceService.getCreated();
    }

    @RequestMapping(
            value = "/send",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public Faktura sendInvoice(@RequestBody Faktura faktura){
        Firma firma = firmRepository.findByPib(faktura.getPibKupca());
        loger.info(firma.getAddress());
        return invoiceService.sentInvoice(faktura, firma.getAddress());
    }

    @RequestMapping(
            value = "/receive",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    @ResponseBody
    public Faktura receiveInvoice(@RequestBody Faktura faktura){

        return invoiceService.receiveInvoice(faktura);
    }

    @RequestMapping(
            value = "/show/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public Faktura getInvoice(@PathVariable String id){

        return invoiceService.getInvoiceById(Long.parseLong(id));
    }

    @RequestMapping(
            value = "/show/received",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public List<Faktura> getReceivedInvoices(){

        return invoiceService.getReceived(true);
    }

    @RequestMapping(
            value = "/show/sent",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public List<Faktura> getSentInvoices(){

        return invoiceService.getSent(true);
    }
}
