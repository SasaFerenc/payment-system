package com.centralnabanka.service.implementation;

import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.service.RtgsService;
import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RtgsServiceImpl implements RtgsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RtgsServiceImpl.class);

    @Autowired
    private BankService bankService;

    @Autowired
    private MessageSenderService messageSender;

    @Autowired
    private MessageConverterService messageConverter;

    @Override
    public Mt900 processMt103Request(Mt103 request) throws Exception {
        LOGGER.info("Processing MT103 request ...");

        String creditorSwiftCode = request.getSwiftKodDuznika();
        String debtorSwiftCode = request.getSwiftKodPoverioca();
        BigDecimal amount = request.getPodaciOPlacanju().getIznos();

        bankService.transfer(creditorSwiftCode, debtorSwiftCode, amount);
        messageSender.sendMessagesToDebtor(request);

        LOGGER.info("Sending MT900 response ...");

        return messageConverter.convertToMt900(request);
    }
}
