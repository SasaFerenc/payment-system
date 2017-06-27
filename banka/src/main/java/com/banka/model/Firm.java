package com.banka.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "firm")
public class Firm implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "firm_id")
    private Long id;

    @Column(name = "firm_name")
    private String name;

}
