package com.firma.service.implementation;

import com.firma.model.Faktura;
import com.firma.repository.InvoiceRepository;
import com.firma.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Faktura save(Faktura faktura) {
        return invoiceRepository.save(faktura);
    }

    @Override
    public Faktura getInvoiceById(Long id) {
        Optional<Faktura> faktura = invoiceRepository.findById(id);
        if(faktura.isPresent())
            return faktura.get();

        return null;
    }

    @Override
    public List<Faktura> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Faktura sentInvoice(Faktura faktura, String url) {

        RestTemplate template = new RestTemplate();
        ResponseEntity<Faktura> response = template.postForEntity(url+"/invoices/receive", faktura, Faktura.class);
        faktura.setSent(true);
        invoiceRepository.save(faktura);
        return response.getBody();
    }

    @Override
    public List<Faktura> getSent(Boolean sent) {
        return invoiceRepository.findBySent(sent);
    }

    @Override
    public List<Faktura> getReceived(Boolean received) {
        return invoiceRepository.findByReceived(received);
    }

    @Override
    public Faktura receiveInvoice(Faktura faktura) {
        faktura.setReceived(true);
        faktura.setSent(false);
        invoiceRepository.save(faktura);
        return faktura;
    }

    @Override
    public List<Faktura> getCreated() {
        return invoiceRepository.findBySentAndReceived(false, false);
    }
}
