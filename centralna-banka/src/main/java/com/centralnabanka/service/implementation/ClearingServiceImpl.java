package com.centralnabanka.service.implementation;

import com.centralnabanka.model.GroupPayment;
import com.centralnabanka.repository.GroupPaymentRepository;
import com.centralnabanka.service.ClearingService;
import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.types.Mt102;
import com.centralnabanka.ws.client.BankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClearingServiceImpl implements ClearingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClearingServiceImpl.class);

    @Autowired
    GroupPaymentRepository paymentRepository;

    @Autowired
    MessageConverterService messageConverter;

    @Autowired
    MessageSenderService messageSender;

    @Autowired
    BankService bankService;

    @Autowired
    BankClient bankClient;

    @Override
    public void saveMt102(Mt102 request) {
        LOGGER.info("Processing MT102 request ...");

        paymentRepository.save(messageConverter.convertToGroupPayment(request));
    }

    @Override
    @Transactional
    public void processPayment(GroupPayment payment) throws Exception {
        bankService.transfer(payment.getCreditorSwiftCode(), payment.getDebtorSwiftCode(), payment.getTotal());

        payment.setSettled(true);
        paymentRepository.save(payment);

        Mt102 mt102 = messageConverter.convertToMt102(payment);
        String creditorBankUrl = bankService.getBankUrlBySwiftCode(payment.getCreditorSwiftCode());

        bankClient.sendMessage(messageConverter.convertToMt900(mt102), creditorBankUrl);
        messageSender.sendMessagesToDebtor(mt102);
    }
}
