package com.banka.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "firm")
public class Firm implements Serializable {

    @GeneratedValue
    @Id
    @Column(name = "firmId")
    private Long id;

    @Column(name = "name")
    private String name;

}
