package com.firma.repository;

import com.firma.model.Nalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by predrag on 6/27/17.
 */
public interface PaymentRepository extends JpaRepository<Nalog, Long> {

    Nalog save(Nalog nalog);

    Optional<Nalog> findById(Long id);

    List<Nalog> findAll();


}
