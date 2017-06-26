package com.firma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by predrag on 6/26/17.
 */

@Entity
@Data
@NoArgsConstructor
public class Presek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "presek")
    private List<StavkaPreseka> stavkePreseka;

    @Column(length = 18)
    @Size(max = 18)
    @NotNull
    private String brojRacuna;

    @NotNull
    private Date datumNaloga;

    @NotNull
    private int brojPreseka;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal prethodnoStanje;

    private int brojPromenaUKorist;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal ukupnoUKorist;

    private int brojPromenaNaTeret;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal ukupnoNaTeret;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal ukupnoStanje;

}
