package com.banka.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "count")
public class Count implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "countNumber")
    private String countNumber;

    @Column(name = "reserved")
    private double reserved;

    @Column(name = "total")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", referencedColumnName = "firmId")
    private Firm firm;

}
