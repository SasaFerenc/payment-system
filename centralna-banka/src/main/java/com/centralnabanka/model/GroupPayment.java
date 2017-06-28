package com.centralnabanka.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "group_payment")
public class GroupPayment extends Base {

    private String messageId;
    private String creditorSwiftCode;
    private String creditorAccountNumber;
    private String debtorSwiftCode;
    private String debtorAccountNumber;
    private BigDecimal total;
    private String valuteCode;
    private Date valuteDate;
    private Date paymentDate;

    private boolean settled = false;

    @OneToMany(mappedBy = "groupPayment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentRequest> paymentRequests;
}
