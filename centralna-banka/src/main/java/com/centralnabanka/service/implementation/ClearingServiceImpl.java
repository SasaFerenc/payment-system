package com.centralnabanka.service.implementation;

import com.centralnabanka.repository.GroupPaymentRepository;
import com.centralnabanka.service.ClearingService;
import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.types.Mt102;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClearingServiceImpl implements ClearingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClearingServiceImpl.class);

    @Autowired
    GroupPaymentRepository paymentRepository;

    @Autowired
    MessageConverterService messageConverter;

    @Override
    public void processMt102Request(Mt102 request) {
        LOGGER.info("Processing Mt102 request ...");

        paymentRepository.save(messageConverter.convertToGroupPayment(request));
    }
}
