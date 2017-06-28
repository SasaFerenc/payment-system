package com.banka.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "account")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "count_number")
    private String countNumber;

    @Column(name = "reserved")
    private BigDecimal reserved;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_id", referencedColumnName = "firm_id")
    private Firm firm;

}
