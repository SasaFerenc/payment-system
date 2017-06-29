package com.firma.repository;


import com.firma.model.Banka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Banka, Long> {

    Optional<Banka> findById(Long id);
}
