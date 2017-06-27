package com.banka.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "count_number")
    private String countNumber;

    @Column(name = "reserved")
    private double reserved;

    @Column(name = "total")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_id", referencedColumnName = "firm_id")
    private Firm firm;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public String getCountNumber() {
        return countNumber;
    }

    public double getReserved() {
        return reserved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountNumber(String countNumber) {
        this.countNumber = countNumber;
    }

    public void setReserved(double reserved) {
        this.reserved = reserved;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public double getTotal() {
        return total;
    }

    public Firm getFirm() {
        return firm;
    }
}
