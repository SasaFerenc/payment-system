package com.banka.services;

import com.banka.model.Account;
import com.banka.model.Bank;
import com.banka.model.PaymentRequest;
import com.banka.repository.AccountRepository;
import com.banka.types.Mt102;
import com.banka.types.Mt103;
import com.banka.types.Nalog;
import com.banka.types.ObjectFactory;
import com.banka.ws.client.CentralBankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountService accountService;

    @Autowired
    BankService bankService;

    @Autowired
    PaymentRequestService paymentRequestService;

    @Autowired
    CentralBankClient centralBankClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    public void handleTransaction(Nalog nalog) {
        List<Account> accountsByNumber = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca());
        if(accountsByNumber.size() > 0) {
            transactionOnTheSameBank(nalog);
        } else {
            if(nalog.getPodaciOPlacanju().getIznos().compareTo(new BigDecimal(250000)) == 1 || nalog.isHitno()) {
                sendRtgs(nalog);
            } else {
                paymentInClearing(nalog);
            }
        }
    }

    public void transactionOnTheSameBank(Nalog nalog) {
        LOGGER.info("IN SAME BANK");
        Account creditorAccount = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunDuznika()).get(0);
        Account debtorAccount = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca()).get(0);
        creditorAccount.setTotal(creditorAccount.getTotal().subtract(nalog.getPodaciOPlacanju().getIznos()));
        debtorAccount.setTotal(debtorAccount.getTotal().add(nalog.getPodaciOPlacanju().getIznos()));

        accountService.save(creditorAccount);
        accountService.save(debtorAccount);
    }

    public void sendRtgs(Nalog nalog) {
        LOGGER.info("SEND RTGS");
        ObjectFactory objectFactory = new ObjectFactory();
        Mt103 mt103 = objectFactory.createMt103();
        mt103.setIdPoruke(nalog.getIdPoruke());

        String creditorAccountCode = nalog.getPodaciOPlacanju().getRacunDuznika().substring(0,3);
        Bank creditorBank = bankService.findByAccountCode(creditorAccountCode);
        String debtorAccountCode = nalog.getPodaciOPlacanju().getRacunPoverioca().substring(0,3);
        Bank debtorBank = bankService.findByAccountCode(debtorAccountCode);

        //reserving resources
        Account creditorAccount = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunDuznika()).get(0);
        creditorAccount.setReserved((creditorAccount.getReserved()).add(nalog.getPodaciOPlacanju().getIznos()));
        accountService.save(creditorAccount);

        mt103.setObracunskiRacunPoverioca(debtorBank.getAccountNumber());
        mt103.setSwiftKodPoverioca(debtorBank.getSwiftCode());
        mt103.setObracunskiRacunDuznika(creditorBank.getAccountNumber());
        mt103.setSwiftKodDuznika(creditorBank.getSwiftCode());
        mt103.setSifraValute(nalog.getOznakaValute());
        mt103.setPodaciOPlacanju(nalog.getPodaciOPlacanju());
        centralBankClient.sendMT103(mt103);
    }

    public void paymentInClearing(Nalog nalog) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setValuteCode(nalog.getOznakaValute());
        paymentRequest.setCreditorName(nalog.getPodaciOPlacanju().getDuznikNalogodavac());
        paymentRequest.setPurpose(nalog.getPodaciOPlacanju().getSvrhaPlacanja());
        paymentRequest.setDebtorName(nalog.getPodaciOPlacanju().getPrimalacPoverilac());
        paymentRequest.setPaymentDate(nalog.getPodaciOPlacanju().getDatumNaloga().toGregorianCalendar().getTime());
        paymentRequest.setValuteDate(nalog.getPodaciOPlacanju().getDatumValute().toGregorianCalendar().getTime());
        paymentRequest.setCreditorAccountNumber(nalog.getPodaciOPlacanju().getRacunDuznika());
        paymentRequest.setChargeModel(nalog.getPodaciOPlacanju().getModelZaduzenja());
        paymentRequest.setDebitReferenceNumber(nalog.getPodaciOPlacanju().getPozivNaBrojZaduzenja());
        paymentRequest.setDebtorAccountNumber(nalog.getPodaciOPlacanju().getRacunPoverioca());
        paymentRequest.setAllowanceModel(nalog.getPodaciOPlacanju().getModelOdobrenja());
        paymentRequest.setCreditReferenceNumber(nalog.getPodaciOPlacanju().getPozivNaBrojOdobrenja());
        paymentRequest.setAmount(nalog.getPodaciOPlacanju().getIznos());
        paymentRequest.setSettled(false);
        paymentRequestService.save(paymentRequest);

        //reserving resources
        Account creditorAccount = accountService.findByCountNumber(nalog.getPodaciOPlacanju().getRacunDuznika()).get(0);
        creditorAccount.setReserved((creditorAccount.getReserved()).add(nalog.getPodaciOPlacanju().getIznos()));
        accountService.save(creditorAccount);
    }

}
