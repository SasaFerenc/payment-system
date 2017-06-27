package com.centralnabanka.service.implementation;

import com.centralnabanka.model.Bank;
import com.centralnabanka.repository.BankRepository;
import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.ws.client.BankClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapFaultException;

@Service
public class MessageSenderServiceImpl extends WebServiceGatewaySupport implements MessageSenderService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankClient bankClient;

    @Autowired
    private MessageConverterService messageConverter;

    @Override
    public void sendMessagesToDebtor(Object message) throws Exception {
        String bankUrl = bankUrl(message);

        bankClient.sendMessage(message, bankUrl);
        bankClient.sendMessage(messageConverter.convertToMt910(message), bankUrl);
    }

    private String bankUrl(Object message) throws Exception {
        String swiftCode = (String) message.getClass().getMethod("getSwiftKodDuznika", new Class<?>[] {}).invoke(message);

        Bank bank = bankRepository.findBySwiftCode(swiftCode).orElseThrow(() ->
                new SoapFaultException("Bank with SWIFT code: " + swiftCode + " not found"));

        return bank.getUrl();
    }
}
