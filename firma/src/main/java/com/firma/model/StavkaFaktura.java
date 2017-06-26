package com.firma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by Predrag on 6/26/17.
 */

@Entity
@Data
@NoArgsConstructor
public class StavkaFaktura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idFakture")
    private Faktura faktura;

    @NotNull
    private int redniBroj;

    @Column(length = 120)
    @Size(max = 120)
    private String nazivRobeUsluge;

    @Digits(integer = 10, fraction = 2)
    @NotNull
    private BigDecimal kolicina;

    @Column(length = 6)
    @Size(max = 6)
    @NotNull
    private String jedinicaMere;

    @Digits(integer = 10, fraction = 2)
    @NotNull
    private BigDecimal jedinicnaCena;

    @Digits(integer = 12, fraction = 2)
    @NotNull
    private BigDecimal vrednost;

    @Digits(integer = 5, fraction = 2)
    @NotNull
    private BigDecimal procenatRabata;

    @Digits(integer = 12, fraction = 2)
    @NotNull
    private BigDecimal iznosRabata;

    @Digits(integer = 12, fraction = 2)
    @NotNull
    private BigDecimal umanjenoZaRabat;

    @Digits(integer = 12, fraction = 2)
    @NotNull
    private BigDecimal ukupanPorez;



}
