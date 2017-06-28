package com.firma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Predrag on 6/26/17.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StavkaPreseka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presek_id")
    @JsonBackReference
    private Presek presek;

    @Size(max = 255)
    @NotNull
    private String duznikNalogodavac;

    @Size(max = 255)
    @NotNull
    private String svrhaPlacanja;

    @Size(max = 255)
    @NotNull
    private String primalacPoverilac;

    @NotNull
    private Date datumNaloga;

    @NotNull
    private Date datumValute;

    @Column(length = 18)
    @Size(max = 18)
    @NotNull
    private String racunDuznika;

    @NotNull
    private int modelZaduzenja;

    @Column(length = 20)
    @Size(max = 20)
    @NotNull
    private String pozivNaBrojZaduzenja;

    @Column(length = 18)
    @Size(max = 18)
    @NotNull
    private String racunPoverioca;

    @NotNull
    private int modelOdobrenja;

    @Column(length = 20)
    @Size(max = 20)
    @NotNull
    private String pozivNaBrojOdobrenja;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal iznos;

    private Character smer;
}
