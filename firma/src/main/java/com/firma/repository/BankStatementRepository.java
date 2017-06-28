package com.firma.repository;

import com.firma.model.Presek;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by predrag on 6/28/17.
 */
public interface BankStatementRepository extends JpaRepository<Presek, Long> {

    Presek save(Presek presek);
}
