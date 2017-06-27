package com.firma.controller;

import com.firma.types.ObjectFactory;
import com.firma.model.ZahtevZaIzvod;
import org.springframework.stereotype.Controller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

/**
 * Created by predrag on 6/28/17.
 */

@Controller
public class BankStatementController {



    private com.firma.types.ZahtevZaIzvod transofrmZahtev(ZahtevZaIzvod zahtev){
        ObjectFactory factory = new ObjectFactory();
        com.firma.types.ZahtevZaIzvod z = factory.createZahtevZaIzvod();
        z.setBrojRacuna(zahtev.getBrojRacuna());
        z.setRedniBrojPreseka(zahtev.getRedniBrojPreseka());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(zahtev.getDatum());
        try {
            z.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return z;
    }
}
