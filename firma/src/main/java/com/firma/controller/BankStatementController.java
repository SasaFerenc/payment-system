package com.firma.controller;

import com.firma.model.Banka;
import com.firma.repository.BankRepository;
import com.firma.service.BankStatementService;
import com.firma.types.ObjectFactory;
import com.firma.model.ZahtevZaIzvod;
import com.firma.types.Presek;
import com.firma.types.StavkaPreseka;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("statement")
public class BankStatementController {

    private BankStatementService bankStatement;
    private BankRepository bankRepository;

    @Autowired
    public BankStatementController(BankStatementService bankStatement, BankRepository bankRepository){
        this.bankStatement = bankStatement;
        this.bankRepository = bankRepository;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseBody
    public com.firma.model.Presek requestStatement(@RequestBody ZahtevZaIzvod zahtev){
        Optional<Banka> banka = bankRepository.findById(1L);
        if(banka.isPresent()) {
            com.firma.model.Presek presek = transformPresek(bankStatement.sendStatementRequest(transformZahtev(zahtev), banka.get().getAddress()));
            return bankStatement.saveBankStatement(presek);
        }
        return null;
    }

    private com.firma.types.ZahtevZaIzvod transformZahtev(ZahtevZaIzvod zahtev){
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

    private com.firma.model.Presek transformPresek(Presek presek){
        com.firma.model.Presek p = new com.firma.model.Presek();
        p.setBrojPreseka(presek.getZaglavljePreseka().getBrojPreseka());
        p.setBrojPromenaNaTeret(presek.getZaglavljePreseka().getBrojPromenaNaTeret());
        p.setBrojPromenaUKorist(presek.getZaglavljePreseka().getBrojPromenaNaTeret());
        p.setBrojRacuna(presek.getZaglavljePreseka().getBrojRacuna());
        p.setDatumNaloga(presek.getZaglavljePreseka().getDatumNaloga().toGregorianCalendar().getTime());
        p.setBrojRacuna(presek.getZaglavljePreseka().getBrojRacuna());
        p.setUkupnoUKorist(presek.getZaglavljePreseka().getUkupnoUKorist());
        p.setUkupnoNaTeret(presek.getZaglavljePreseka().getUkupnoNaTeret());
        p.setUkupnoStanje(presek.getZaglavljePreseka().getUkupnoStanje());
        List<com.firma.model.StavkaPreseka> list = presek.getStavkaPreseka().stream().map(stav -> transformStavkaPreseka(stav))
                                                                                     .collect(Collectors.toList());
        p.setStavkePreseka(list);
        return p;
    }

    private com.firma.model.StavkaPreseka transformStavkaPreseka(StavkaPreseka stavka){
        com.firma.model.StavkaPreseka s = new com.firma.model.StavkaPreseka();
        s.setSmer(stavka.getSmer());
        s.setSvrhaPlacanja(stavka.getPodaciOPlacanju().getSvrhaPlacanja());
        s.setRacunPoverioca(stavka.getPodaciOPlacanju().getRacunPoverioca());
        s.setRacunDuznika(stavka.getPodaciOPlacanju().getRacunDuznika());
        s.setPrimalacPoverilac(stavka.getPodaciOPlacanju().getPrimalacPoverilac());
        s.setPozivNaBrojZaduzenja(stavka.getPodaciOPlacanju().getPozivNaBrojZaduzenja());
        s.setPozivNaBrojOdobrenja(String.valueOf(stavka.getPodaciOPlacanju().getPozivNaBrojOdobrenja()));
        s.setModelZaduzenja(stavka.getPodaciOPlacanju().getModelZaduzenja());
        s.setModelOdobrenja(stavka.getPodaciOPlacanju().getModelOdobrenja());
        s.setIznos(stavka.getPodaciOPlacanju().getIznos());
        s.setDuznikNalogodavac(stavka.getPodaciOPlacanju().getDuznikNalogodavac());
        s.setDatumNaloga(stavka.getPodaciOPlacanju().getDatumNaloga().toGregorianCalendar().getTime());
        s.setDatumValute(stavka.getPodaciOPlacanju().getDatumValute().toGregorianCalendar().getTime());
        return s;
    }

}
