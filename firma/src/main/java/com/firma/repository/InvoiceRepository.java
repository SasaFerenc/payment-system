package com.firma.repository;

import com.firma.model.Faktura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Predrag on 6/27/17.
 */

public interface InvoiceRepository extends JpaRepository<Faktura, Long> {

    Faktura save(Faktura s);

    Optional<Faktura> findById(Long id);

    List<Faktura> findBySent(Boolean sent);

    List<Faktura> findByRecieved(Boolean recieved);

}
