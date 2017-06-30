package com.banka.services;

import com.banka.model.StatementPayment;
import com.banka.types.Mt102;
import com.banka.types.Mt103;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementConversionService {

    @Autowired
    StatementPaymentService statementPaymentService;

    public void saveStatementPaymentFrom103(Mt103 mt103, boolean outgoing) {
        StatementPayment statementPayment = new StatementPayment();

        statementPayment.setCreditorName(mt103.getPodaciOPlacanju().getDuznikNalogodavac());
        statementPayment.setPurpose(mt103.getPodaciOPlacanju().getSvrhaPlacanja());
        statementPayment.setDebtorName(mt103.getPodaciOPlacanju().getPrimalacPoverilac());
        statementPayment.setPaymentDate(mt103.getPodaciOPlacanju().getDatumNaloga().toGregorianCalendar().getTime());
        statementPayment.setValuteDate(mt103.getPodaciOPlacanju().getDatumValute().toGregorianCalendar().getTime());
        statementPayment.setCreditorAccountNumber(mt103.getPodaciOPlacanju().getRacunDuznika());
        statementPayment.setChargeModel(mt103.getPodaciOPlacanju().getModelZaduzenja());
        statementPayment.setDebitReferenceNumber(mt103.getPodaciOPlacanju().getPozivNaBrojZaduzenja());
        statementPayment.setDebtorAccountNumber(mt103.getPodaciOPlacanju().getRacunPoverioca());
        statementPayment.setAllowanceModel(mt103.getPodaciOPlacanju().getModelOdobrenja());
        statementPayment.setAmount(mt103.getPodaciOPlacanju().getIznos());
        statementPayment.setOutgoing(outgoing);

        statementPaymentService.save(statementPayment);
    }

    public void saveStatementPaymentFrom102(Mt102 mt102, boolean outgoing) {

        for(Mt102.PojedinacnaPlacanja pojedinacnaPlacanja : mt102.getPojedinacnaPlacanja()) {
            StatementPayment statementPayment = new StatementPayment();

            statementPayment.setCreditorName(pojedinacnaPlacanja.getPodaciOPlacanju().getDuznikNalogodavac());
            statementPayment.setPurpose(pojedinacnaPlacanja.getPodaciOPlacanju().getSvrhaPlacanja());
            statementPayment.setDebtorName(pojedinacnaPlacanja.getPodaciOPlacanju().getPrimalacPoverilac());
            statementPayment.setPaymentDate(pojedinacnaPlacanja.getPodaciOPlacanju().getDatumNaloga().toGregorianCalendar().getTime());
            statementPayment.setValuteDate(pojedinacnaPlacanja.getPodaciOPlacanju().getDatumValute().toGregorianCalendar().getTime());
            statementPayment.setCreditorAccountNumber(pojedinacnaPlacanja.getPodaciOPlacanju().getRacunDuznika());
            statementPayment.setChargeModel(pojedinacnaPlacanja.getPodaciOPlacanju().getModelZaduzenja());
            statementPayment.setDebitReferenceNumber(pojedinacnaPlacanja.getPodaciOPlacanju().getPozivNaBrojZaduzenja());
            statementPayment.setDebtorAccountNumber(pojedinacnaPlacanja.getPodaciOPlacanju().getRacunPoverioca());
            statementPayment.setAllowanceModel(pojedinacnaPlacanja.getPodaciOPlacanju().getModelOdobrenja());
            statementPayment.setAmount(pojedinacnaPlacanja.getPodaciOPlacanju().getIznos());
            statementPayment.setOutgoing(outgoing);

            statementPaymentService.save(statementPayment);
        }

    }



}
