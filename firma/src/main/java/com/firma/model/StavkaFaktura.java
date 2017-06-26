package com.firma.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Predrag on 6/26/17.
 */

@Entity
public class StavkaFaktura {

    @ManyToOne
    @JoinColumn(name = "faktura_id")
    private Faktura faktura;
}
