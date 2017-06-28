package com.centralnabanka.service.implementation;

import com.centralnabanka.repository.GroupPaymentRepository;
import com.centralnabanka.service.ClearingService;
import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.types.Mt102;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClearingServiceImpl implements ClearingService {

    @Autowired
    GroupPaymentRepository paymentRepository;

    @Autowired
    MessageConverterService messageConverter;

    @Override
    public void processMt102Request(Mt102 request) {
        paymentRepository.save(messageConverter.convertToGroupPayment(request));
    }
}
