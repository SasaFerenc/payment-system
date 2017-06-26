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
 * Created by Predrag on 6/26/17.
 */

@Entity
@Data
@NoArgsConstructor
public class Faktura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "faktura")
    private List<StavkaFaktura> stavkaFakture;

    @Column(length = 50)
    @Size(max = 50)
    @NotNull
    private String idPoruke;

    @Size(max = 255)
    @NotNull
    private String nazivDobavljaca;

    @Size(max = 255)
    @NotNull
    private String adresaDobavljaca;

    @Column(length = 11)
    @Size(max = 11)
    @NotNull
    private String pibDobavljaca;

    @Column(length = 55)
    @Size(max = 55)
    @NotNull
    private String nazivKupca;

    @Column(length = 55)
    @Size(max = 55)
    @NotNull
    private String adresaKupca;

    @Column(length = 11)
    @Size(max = 11)
    @NotNull
    private String pibKupca;

    @NotNull
    private int brojRacuna;

    @NotNull
    private Date datumRacuna;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal vrednostRobe;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal vrednostUsluga;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal vrednostRobeUsluga;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal ukupanRabat;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal ukupanPorez;

    @Column(length = 3)
    @Size(max = 3)
    @NotNull
    private String oznakaValute;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal iznosZaUplatu;

    @Column(length = 18)
    @Size(max = 18)
    private String uplataNaRacun;

    private Date datumValute;
}
