package com.banka.services;

import com.banka.model.Bank;
import com.banka.model.PaymentRequest;
import com.banka.types.Mt102;
import com.banka.types.ObjectFactory;
import com.banka.types.PodaciOPlacanju;
import com.banka.ws.client.CentralBankClient;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.rmi.server.UID;
import java.util.*;

@Service
public class SettlementScheduler {

    @Autowired
    BankService bankService;

    @Autowired
    PaymentRequestService paymentRequestService;

    @Autowired
    CentralBankClient centralBankClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementScheduler.class);

    @Scheduled(fixedDelay = 10000)
    public void executeClearing() {
        List<Bank> banks = bankService.findAll();
        for(Bank b : banks) {
            if(!b.isLocal()) {
                try {
                    String bankCode = b.getAccountNumber().substring(0, 3);
                    List<PaymentRequest> paymentRequests = paymentRequestService.findByBankCode(bankCode);
                    if(paymentRequests.size() > 0 && paymentRequests.stream().filter(request -> !request.isSettled()).count() > 0) {
                        Mt102 mt102 = makeMt102(b, paymentRequests);
                        centralBankClient.sendMT102(mt102);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Mt102 makeMt102(Bank bank, List<PaymentRequest> paymentRequests) throws Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        Mt102 mt102 = objectFactory.createMt102();
        String messageId = UUID.randomUUID().toString();
        Bank local = bankService.findByLocal(true);

        mt102.setIdPoruke(messageId);
        mt102.setSwiftKodDuznika(local.getSwiftCode());
        mt102.setObracunskiRacunDuznika(local.getAccountNumber());
        mt102.setSwiftKodPoverioca(bank.getSwiftCode());
        mt102.setObracunskiRacunPoverioca(bank.getAccountNumber());
        mt102.setUkupanIznos(new BigDecimal(0));
        mt102.setSifraValute(paymentRequests.get(0).getValuteCode());
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        mt102.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
        mt102.setDatumValute(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
       for(PaymentRequest request : paymentRequests) {
           if(!request.isSettled()) {
               Mt102.PojedinacnaPlacanja pojedinacnaPlacanja = new Mt102.PojedinacnaPlacanja();

               PodaciOPlacanju podaciOPlacanju = new PodaciOPlacanju();
               podaciOPlacanju.setDuznikNalogodavac(request.getCreditorName());
               podaciOPlacanju.setSvrhaPlacanja(request.getPurpose());
               podaciOPlacanju.setPrimalacPoverilac(request.getDebtorName());
               GregorianCalendar gregorianDatumNaloga = new GregorianCalendar();
               gregorianDatumNaloga.setTime(request.getPaymentDate());
               podaciOPlacanju.setDatumNaloga(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianDatumNaloga));
               GregorianCalendar gregorianDatumValute = new GregorianCalendar();
               gregorianDatumValute.setTime(request.getValuteDate());
               podaciOPlacanju.setDatumValute(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianDatumValute));
               podaciOPlacanju.setRacunDuznika(request.getCreditorAccountNumber());
               podaciOPlacanju.setModelZaduzenja(request.getChargeModel());
               podaciOPlacanju.setPozivNaBrojZaduzenja(request.getDebitReferenceNumber());
               podaciOPlacanju.setRacunPoverioca(request.getDebtorAccountNumber());
               podaciOPlacanju.setModelOdobrenja(request.getAllowanceModel());
               podaciOPlacanju.setPozivNaBrojOdobrenja(request.getCreditReferenceNumber());
               podaciOPlacanju.setIznos(request.getAmount());
               mt102.setUkupanIznos(mt102.getUkupanIznos().add(request.getAmount()));

               pojedinacnaPlacanja.setIdNaloga(UUID.randomUUID().toString());
               pojedinacnaPlacanja.setPodaciOPlacanju(podaciOPlacanju);
               pojedinacnaPlacanja.setSifraValute(request.getValuteCode());

               mt102.getPojedinacnaPlacanja().add(pojedinacnaPlacanja);
           }
       }

       for(int i = 0; i < paymentRequests.size(); i++) {
           PaymentRequest request = paymentRequests.get(i);
           if(!request.isSettled()) {
               request.setSettled(true);
               paymentRequestService.save(request);
           }
       }

       return mt102;
    }

}
