package com.banka.repository;

import com.banka.model.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {

    Firm findOne(Long id);
    List<Firm> findByName(String name);
    Firm save(Firm firm);
    void delete(Long id);

}
