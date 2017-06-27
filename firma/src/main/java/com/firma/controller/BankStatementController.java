package com.firma.controller;

import com.firma.service.BankStatementService;
import com.firma.types.ObjectFactory;
import com.firma.model.ZahtevZaIzvod;
import com.firma.types.Presek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

/**
 * Created by predrag on 6/28/17.
 */

@Controller
@RequestMapping("statement")
public class BankStatementController {

    private BankStatementService bankStatement;

    @Autowired
    public BankStatementController(BankStatementService bankStatement){
        this.bankStatement = bankStatement;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public void requestStatement(@RequestBody ZahtevZaIzvod zahtev){
        Presek presek = bankStatement.sendStatementRequest(transoformZahtev(zahtev));
    }

    private com.firma.types.ZahtevZaIzvod transoformZahtev(ZahtevZaIzvod zahtev){
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
