package com.centralnabanka.service.implementation;

import com.centralnabanka.model.Bank;
import com.centralnabanka.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.soap.SoapFaultException;

import java.math.BigDecimal;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    @Transactional
    public void transfer(String creditorSwiftCode, String debtorSwiftCode, BigDecimal transferAmount) {
        Bank debtor = getBankBySwiftCode(debtorSwiftCode);
        Bank creditor = getBankBySwiftCode(creditorSwiftCode);
        BigDecimal newCreditorAccountBalance = creditor.getAccountBalance().subtract(transferAmount);

        if (newCreditorAccountBalance.signum() == -1) {
            throw new SoapFaultException("Bank with SWIFT code: " + creditorSwiftCode + " has negative account balance");
        }

        creditor.setAccountBalance(newCreditorAccountBalance);
        bankRepository.save(creditor);

        debtor.setAccountBalance(debtor.getAccountBalance().add(transferAmount));
        bankRepository.save(debtor);
    }

    @Override
    public String getBankUrlBySwiftCode(String swiftCode) {
        return getBankBySwiftCode(swiftCode).getUrl();
    }

    private Bank getBankBySwiftCode(String swiftCode) {
        return bankRepository.findBySwiftCode(swiftCode).orElseThrow(() ->
                new SoapFaultException("Bank with SWIFT code: " + swiftCode + " not found"));
    }
}
