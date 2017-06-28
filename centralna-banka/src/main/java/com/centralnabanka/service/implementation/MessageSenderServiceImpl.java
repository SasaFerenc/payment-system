package com.centralnabanka.service.implementation;

import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.ws.client.BankClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderServiceImpl implements MessageSenderService {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankClient bankClient;

    @Autowired
    private MessageConverterService messageConverter;

    @Override
    public void sendMessagesToDebtor(Object message) throws Exception {
        String bankUrl = bankService.getBankUrlBySwiftCode(swiftCode(message));

        bankClient.sendMessage(message, bankUrl);
        bankClient.sendMessage(messageConverter.convertToMt910(message), bankUrl);
    }

    private String swiftCode(Object message) throws Exception {
        return (String) message.getClass().getMethod("getSwiftKodDuznika", new Class<?>[] {}).invoke(message);
    }
}
