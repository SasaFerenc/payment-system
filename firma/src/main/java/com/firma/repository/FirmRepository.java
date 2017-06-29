package com.firma.repository;


import com.firma.model.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firma, Long> {

    Firma findByPib(String pib);
}
