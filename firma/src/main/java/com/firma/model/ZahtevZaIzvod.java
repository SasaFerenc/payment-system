package com.firma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Predrag on 6/26/17.
 */

@Entity
@Data
@NoArgsConstructor
public class ZahtevZaIzvod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 18)
    @Size(max = 18)
    @NotNull
    private String brojRacuna;

    @NotNull
    private Date datum;

    @NotNull
    private int redniBrojPreseka;
}
