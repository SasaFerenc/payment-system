package com.firma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Nalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Size(max = 50)
    @NotNull
    private String idPoruke;

    @Column(length = 255)
    @Size(max = 255)
    @NotNull
    private String duznikNalogodavac;

    @Column(length = 255)
    @Size(max = 255)
    @NotNull
    private String svrhaPlacanja;

    @Column(length = 255)
    @Size(max = 255)
    @NotNull
    private String primalacPoverilac;

    @NotNull
    private Date datumNaloga;

    @NotNull
    private Date datumValute;

    @Column(length = 18)
    @Size(max = 18)
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

    @NotNull
    private int pozivNaBrojOdobrenja;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal iznos;

    @Column(length = 3)
    @Size(max = 3)
    @NotNull
    private String oznakaValute;

    @NotNull
    private Boolean hitno;

    @Override
    public String toString() {
        return "Nalog{" +
                "id=" + id +
                ", idPoruke='" + idPoruke + '\'' +
                ", duznikNalogodavac='" + duznikNalogodavac + '\'' +
                ", svrhaPlacanja='" + svrhaPlacanja + '\'' +
                ", primalacPoverilac='" + primalacPoverilac + '\'' +
                ", datumNaloga=" + datumNaloga +
                ", datumValute=" + datumValute +
                ", racunDuznika='" + racunDuznika + '\'' +
                ", modelZaduzenja=" + modelZaduzenja +
                ", pozivNaBrojZaduzenja='" + pozivNaBrojZaduzenja + '\'' +
                ", racunPoverioca='" + racunPoverioca + '\'' +
                ", modelOdobrenja=" + modelOdobrenja +
                ", pozivNaBrojOdobrenja=" + pozivNaBrojOdobrenja +
                ", iznos=" + iznos +
                ", oznakaValute='" + oznakaValute + '\'' +
                ", hitno=" + hitno +
                '}';
    }
}
