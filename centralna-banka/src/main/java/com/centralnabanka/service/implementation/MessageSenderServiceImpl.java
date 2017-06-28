package com.centralnabanka.service.implementation;

import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.ws.client.BankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderServiceImpl implements MessageSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RtgsServiceImpl.class);

    @Autowired
    private BankService bankService;

    @Autowired
    private BankClient bankClient;

    @Autowired
    private MessageConverterService messageConverter;

    @Override
    public void sendMessagesToDebtor(Object message) throws Exception {
        LOGGER.info("Sending messages to bank: " + swiftCode(message));

        String bankUrl = bankService.getBankUrlBySwiftCode(swiftCode(message));

        bankClient.sendMessage(message, bankUrl);
        bankClient.sendMessage(messageConverter.convertToMt910(message), bankUrl);
    }

    private String swiftCode(Object message) throws Exception {
        return (String) message.getClass().getMethod("getSwiftKodPoverioca", new Class<?>[] {}).invoke(message);
    }
}
