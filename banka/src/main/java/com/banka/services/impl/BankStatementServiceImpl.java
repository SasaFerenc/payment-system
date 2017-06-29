package com.banka.services.impl;

import com.banka.model.StatementPayment;
import com.banka.repository.StatementPaymentRepository;
import com.banka.services.BankStatementService;
import com.banka.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class BankStatementServiceImpl implements BankStatementService {

    private static final int ITEMS_PER_PAGE = 5;

    @Autowired
    StatementPaymentRepository statementPaymentRepository;

    @Override
    public Presek processBankStatementRequest(ZahtevZaIzvod request) throws Exception {
        String accountNumber = request.getBrojRacuna();
        Date statementDate = request.getDatum().toGregorianCalendar().getTime();
        int pageIndex = request.getRedniBrojPreseka();

        Presek page = new Presek();

        List<StatementPayment> resultSet = statementPaymentRepository.getByAccountNumberAndDate(accountNumber, statementDate);
        int totalPages = (int) Math.ceil(resultSet.size() / (double) ITEMS_PER_PAGE);

        ZaglavljePreseka header = new ZaglavljePreseka();
        header.setDatumNaloga(request.getDatum());
        header.setBrojRacuna(accountNumber);
        header.setBrojPreseka(totalPages);
        header.setBrojPromenaUKorist(countIncoming(resultSet));
        header.setBrojPromenaNaTeret(countOutgoing(resultSet));
        header.setUkupnoUKorist(new BigDecimal(incomingAmount(resultSet)));
        header.setUkupnoNaTeret(new BigDecimal(outgongAmount(resultSet)));

        page.setZaglavljePreseka(header);

        int from = pageIndex * ITEMS_PER_PAGE;
        int to = from + ITEMS_PER_PAGE;

        if (to > resultSet.size()) {
           to = resultSet.size();
        }

        for (StatementPayment payment : resultSet.subList(from, to)) {
            StavkaPreseka item = new StavkaPreseka();
            item.setSmer(payment.isOutgoing() ? "O" : "I");

            PodaciOPlacanju info = new PodaciOPlacanju();
            info.setDuznikNalogodavac(payment.getCreditorName());
            info.setSvrhaPlacanja(payment.getPurpose());
            info.setPrimalacPoverilac(payment.getDebtorName());
            info.setDatumNaloga(toGregorianCalendarDate(payment.getPaymentDate()));
            info.setDatumValute(toGregorianCalendarDate(payment.getValuteDate()));
            info.setRacunDuznika(payment.getCreditorAccountNumber());
            info.setModelZaduzenja(payment.getChargeModel());
            info.setPozivNaBrojZaduzenja(payment.getDebitReferenceNumber());
            info.setRacunPoverioca(payment.getDebtorAccountNumber());
            info.setModelOdobrenja(payment.getAllowanceModel());
            info.setPozivNaBrojOdobrenja(423555333);
            info.setIznos(payment.getAmount());

            item.setPodaciOPlacanju(info);

            page.getStavkaPreseka().add(item);
        }

        return page;
    }

    private int countOutgoing(List<StatementPayment> resultSet) {
       return (int) resultSet.stream().filter(statementPayment -> statementPayment.isOutgoing()).count();
    }

    private int countIncoming(List<StatementPayment> resultSet) {
        return (int) resultSet.stream().filter(statementPayment -> !statementPayment.isOutgoing()).count();
    }

    private double incomingAmount(List<StatementPayment> resultSet) {
        return resultSet
            .stream()
            .filter(statementPayment -> !statementPayment.isOutgoing())
            .mapToDouble(statementPayment -> statementPayment.getAmount().doubleValue())
            .sum();
    }

    private double outgongAmount(List<StatementPayment> resultSet) {
        return resultSet
                .stream()
                .filter(statementPayment -> statementPayment.isOutgoing())
                .mapToDouble(statementPayment -> statementPayment.getAmount().doubleValue())
                .sum();
    }

    private XMLGregorianCalendar toGregorianCalendarDate(Date date) throws Exception {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }
}
