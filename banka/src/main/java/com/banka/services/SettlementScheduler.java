package com.banka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SettlementScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementScheduler.class);

    @Scheduled(fixedDelay = 10000)
    public void executeClearing() {
        LOGGER.info("LOGOVAO");
    }

}
