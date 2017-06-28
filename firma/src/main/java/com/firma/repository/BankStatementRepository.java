package com.firma.repository;

import com.firma.model.Presek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankStatementRepository extends JpaRepository<Presek, Long> {

    Presek save(Presek presek);
}
