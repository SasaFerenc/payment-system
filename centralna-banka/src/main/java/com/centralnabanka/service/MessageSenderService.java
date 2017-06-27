package com.centralnabanka.service;

public interface MessageSenderService {

    void sendMessagesToDebtor(Object message) throws Exception;
}
