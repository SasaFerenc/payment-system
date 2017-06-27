package com.firma.service.implementation;

import com.firma.model.Nalog;
import com.firma.repository.PaymentRepository;
import com.firma.service.PaymentService;
import com.firma.types.PodaciOPlacanju;
import com.firma.ws.client.FirmClient;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by Predrag on 6/27/17.
 */

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
    public void send() {
        com.firma.types.Nalog n = new com.firma.types.Nalog();
        n.setHitno(false);
        n.setIdPoruke("IdPoruke");
        n.setOznakaValute("RSD");
        PodaciOPlacanju podaci = new PodaciOPlacanju();
        podaci.setDatumNaloga(new XMLGregorianCalendarImpl());
        podaci.setDatumValute(new XMLGregorianCalendarImpl());
        podaci.setDuznikNalogodavac("Duznik");
        podaci.setIznos(new BigDecimal(10000));
        podaci.setModelOdobrenja(97);
        podaci.setModelZaduzenja(56);
        podaci.setPozivNaBrojOdobrenja(1234);
        podaci.setPozivNaBrojZaduzenja("3546");
        podaci.setPrimalacPoverilac("Primalac");
        podaci.setRacunDuznika("Racun duznika");
        podaci.setRacunPoverioca("Racun poverioca");
        podaci.setSvrhaPlacanja("Kupio sam nesto");
        n.setPodaciOPlacanju(podaci);
        firmClient.sendPayment(n);
    }

}
