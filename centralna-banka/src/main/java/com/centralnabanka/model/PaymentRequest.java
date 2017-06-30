package com.centralnabanka.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    private Date paymentDate;
    private Date valuteDate;
    private String creditorAccountNumber;
    private int chargeModel;
    private String debitReferenceNumber;
    private String debtorAccountNumber;
    private int allowanceModel;
    private int creditReferenceNumber;
    private BigDecimal amount;
    private String valuteCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_payment_id")
    private GroupPayment groupPayment;
}
