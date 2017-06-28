package com.firma.service;

import com.firma.model.Faktura;

import java.util.List;

/**
 * Created by Predrag on 6/27/17.
 */

public interface InvoiceService {

    Faktura save(Faktura faktura);

    Faktura getInvoiceById(Long id);

    List<Faktura> getAll();

    Faktura sentInvoice(Faktura faktura);

    List<Faktura> getSent(Boolean sent);

    List<Faktura> getReceived(Boolean received);

    Faktura receiveInvoice(Faktura faktura);

    List<Faktura> getCreated();


}
