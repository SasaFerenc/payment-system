package com.centralnabanka.service.implementation;

import com.centralnabanka.model.Bank;
import com.centralnabanka.repository.BankRepository;
import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.service.RtgsService;
import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapFaultException;

import java.math.BigDecimal;

@Service
public class RtgsServiceImpl extends WebServiceGatewaySupport implements RtgsService {

    private BankRepository bankRepository;
    private MessageSenderService messageSender;

    @Autowired
    public RtgsServiceImpl(BankRepository bankRepository, MessageSenderService messageSender) {
        this.bankRepository = bankRepository;
        this.messageSender = messageSender;
    }

    @Override
    public Mt900 processMt103Request(Mt103 request) {
        transferMoney(request);

        messageSender.sendMessagesToDebtor(request);

        return messageSender.createMt900(request);
    }

    @Transactional
    private void transferMoney(Mt103 request) {
        String debtorSwiftCode = request.getSwiftKodDuznika();
        Bank debtor = bankRepository.findBySwiftCode(debtorSwiftCode).orElseThrow(() ->
            new SoapFaultException("Bank with SWIFT code: " + debtorSwiftCode + " not found."));

        String creditorSwiftCode = request.getSwiftKodPoverioca();
        Bank creditor = bankRepository.findBySwiftCode(creditorSwiftCode).orElseThrow(() ->
                new SoapFaultException("Bank with SWIFT code: " + creditorSwiftCode + " not found."));

        BigDecimal transferAmount = request.getPodaciOPlacanju().getIznos();
        BigDecimal newCreditorAccountBalance = creditor.getAccountBalance().subtract(transferAmount);

        if (newCreditorAccountBalance.signum() == -1) {
            throw new SoapFaultException("Bank with SWIFT code: " + creditorSwiftCode + " has negative account balance");
        }

        creditor.setAccountBalance(newCreditorAccountBalance);
        bankRepository.save(creditor);

        debtor.setAccountBalance(debtor.getAccountBalance().add(transferAmount));
        bankRepository.save(debtor);
    }
}
