package com.banka.services.impl;

import com.banka.model.Firm;
import com.banka.repository.FirmRepository;
import com.banka.services.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    FirmRepository firmRepository;

    @Override
    public Firm findOne(Long id) {
        return firmRepository.getOne(id);
    }

    @Override
    public List<Firm> findByName(String name) {
        return firmRepository.findByName(name);
    }

    @Override
    public Firm save(Firm firm) {
        return firmRepository.save(firm);
    }

    @Override
    public void delete(Long id) {
        firmRepository.deleteById(id);
    }
}
