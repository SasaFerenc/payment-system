package com.centralnabanka.service.implementation;

import java.math.BigDecimal;

public interface BankService {

    void transfer(String creditorSwiftCode, String debtorSwiftCode, BigDecimal amount);

    String getBankUrlBySwiftCode(String swiftCode);
}
