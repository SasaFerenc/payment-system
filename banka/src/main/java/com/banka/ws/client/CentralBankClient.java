package com.banka.ws.client;

import com.banka.types.Mt103;
import com.banka.types.Mt900;
import com.banka.types.ObjectFactory;
import com.banka.types.PodaciOPlacanju;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class CentralBankClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CentralBankClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    /*@PostConstruct
    public void init() {
        Mt900 res = sendMT103("ooo");
    }*/

    public Mt900 sendMT103(String id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Mt103 mt103 = objectFactory.createMt103();

        mt103.setIdPoruke(id);
        mt103.setObracunskiRacunDuznika("kk");
        mt103.setObracunskiRacunPoverioca("ll");

        PodaciOPlacanju podaciOPlacanju = new PodaciOPlacanju();
        podaciOPlacanju.setDatumNaloga(new XMLGregorianCalendarImpl());
        podaciOPlacanju.setDatumValute(new XMLGregorianCalendarImpl());
        podaciOPlacanju.setDuznikNalogodavac("a");
        podaciOPlacanju.setIznos(new BigDecimal(5));
        podaciOPlacanju.setModelOdobrenja(1);
        podaciOPlacanju.setModelZaduzenja(1);
        podaciOPlacanju.setPozivNaBrojOdobrenja(1);
        podaciOPlacanju.setPozivNaBrojZaduzenja("sa");
        podaciOPlacanju.setPrimalacPoverilac("a");
        podaciOPlacanju.setRacunDuznika("a");
        podaciOPlacanju.setRacunPoverioca("aa");
        podaciOPlacanju.setSvrhaPlacanja("aa");

        mt103.setPodaciOPlacanju(podaciOPlacanju);
        mt103.setSifraValute("a");
        mt103.setSwiftKodDuznika("s");
        mt103.setSwiftKodPoverioca("d");

        Mt900 response = (Mt900) webServiceTemplate.marshalSendAndReceive(mt103);
        LOGGER.info("900 doslo: " + response.getIdPoruke());
        return response;
    }

}
