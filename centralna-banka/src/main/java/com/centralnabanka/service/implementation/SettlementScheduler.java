package com.centralnabanka.service.implementation;

import com.centralnabanka.model.GroupPayment;
import com.centralnabanka.repository.GroupPaymentRepository;
import com.centralnabanka.service.ClearingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SettlementScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementScheduler.class);

    @Autowired
    ClearingService clearingService;

    @Autowired
    GroupPaymentRepository paymentRepository;

    @Scheduled(fixedDelay = 1000 * 10)
    public void execute() throws Exception {
        for (GroupPayment payment : paymentRepository.findBySettledFalse()) {
            LOGGER.warn("Processing " + payment.getMessageId() + " payment batch ...");

            clearingService.processPayment(payment);

            LOGGER.info("Done [" + payment.getMessageId() + "]");
        }
    }
}
