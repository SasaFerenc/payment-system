package com.firma.controller;

import com.firma.model.Banka;
import com.firma.model.Nalog;
import com.firma.repository.BankRepository;
import com.firma.service.PaymentService;
import com.firma.types.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/payments")
public class PaymentRequestController {

    private static Logger loger = Logger.getLogger("S");
    private PaymentService paymentService;
    private BankRepository bankRepository;

    @Autowired
    public PaymentRequestController(PaymentService paymentService, BankRepository bankRepository){
        this.paymentService = paymentService;
        this.bankRepository = bankRepository;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Nalog createPayment(@RequestBody Nalog nalog){
        loger.info(nalog.toString());
        return paymentService.createPayment(nalog);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public Nalog getPayment(@PathVariable String id){

        return paymentService.getPaymentById(Long.parseLong(id));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public List<Nalog> getAllPayment(){
        return paymentService.getAllPayments();
    }

    @RequestMapping(
            value = "/send",
            method = RequestMethod.POST
    )
    public void sendPayment(@RequestBody Nalog nalog){
        Optional<Banka> banka = bankRepository.findById(1L);
        com.firma.types.Nalog n = transformPayment(nalog);
        if(banka.isPresent())
            paymentService.send(n, banka.get().getAddress());
        return;
    }

    private com.firma.types.Nalog transformPayment(Nalog nalog){
        ObjectFactory factory = new ObjectFactory();
        com.firma.types.Nalog n = factory.createNalog();
        n.setIdPoruke(nalog.getIdPoruke());
        n.setHitno(nalog.getHitno());
        n.setOznakaValute(nalog.getOznakaValute());
        com.firma.types.PodaciOPlacanju podaci = factory.createPodaciOPlacanju();
        podaci.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
        podaci.setRacunPoverioca(nalog.getRacunPoverioca());
        podaci.setRacunDuznika(nalog.getRacunDuznika());
        podaci.setPrimalacPoverilac(nalog.getPrimalacPoverilac());
        podaci.setPozivNaBrojZaduzenja(nalog.getPozivNaBrojZaduzenja());
        podaci.setPozivNaBrojOdobrenja(nalog.getPozivNaBrojOdobrenja());
        podaci.setModelZaduzenja(nalog.getModelZaduzenja());
        podaci.setModelOdobrenja(nalog.getModelOdobrenja());
        podaci.setIznos(nalog.getIznos());
        podaci.setDuznikNalogodavac(nalog.getDuznikNalogodavac());
        GregorianCalendar datumNal = new GregorianCalendar();
        GregorianCalendar datumVal = new GregorianCalendar();
        datumNal.setTime(nalog.getDatumNaloga());
        datumVal.setTime(nalog.getDatumValute());
        try {
            podaci.setDatumNaloga(DatatypeFactory.newInstance().newXMLGregorianCalendar(datumNal));
            podaci.setDatumValute(DatatypeFactory.newInstance().newXMLGregorianCalendar(datumVal));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        n.setPodaciOPlacanju(podaci);
        return n;
    }
}
