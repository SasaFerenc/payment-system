package com.centralnabanka.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Bank extends Base {

    private String name;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String swiftCode;
    private String url;
}
