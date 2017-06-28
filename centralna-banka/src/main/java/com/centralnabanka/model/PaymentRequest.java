package com.centralnabanka.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payment_request")
public class PaymentRequest extends Base {

    private String paymentId;
    private String creditorName;
    private String purpose;
    private String debtorName;
    private String paymentDate;
    private String creditorAccountNumber;
    private int chargeModel;
    private String debitReferenceNumber;
    private String debtorAccountNumber;
    private int allowanceModel;
    private String creditReferenceNumber;
    private BigDecimal amount;
    private String valuteCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_payment_id")
    private GroupPayment groupPayment;
}
