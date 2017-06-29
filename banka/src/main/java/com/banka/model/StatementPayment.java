package com.banka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "statement_payment")
public class StatementPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creditor_name")
    private String creditorName;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "debtor_name")
    private String debtorName;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "valute_date")
    private Date valuteDate;

    @Column(name = "creditor_account")
    private String creditorAccountNumber;

    @Column(name = "charge_model")
    private int chargeModel;

    @Column(name = "debit_reference_number")
    private String debitReferenceNumber;

    @Column(name = "debtor_account")
    private String debtorAccountNumber;

    @Column(name = "allowance_model")
    private int allowanceModel;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "outgoing")
    private boolean outgoing;

}
