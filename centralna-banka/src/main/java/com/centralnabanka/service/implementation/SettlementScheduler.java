package com.centralnabanka.service.implementation;

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

    @Scheduled(fixedDelay = 1000 * 60)
    public void execute() {
        LOGGER.info("Tic tac");
    }
}
