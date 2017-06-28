package com.banka.services;

import com.banka.model.Firm;

import java.util.List;

public interface FirmService {

    Firm findOne(Long id);
    List<Firm> findByName(String name);
    Firm save(Firm firm);
    void delete(Long id);

}
