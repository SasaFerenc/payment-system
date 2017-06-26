package com.centralnabanka.service;

import com.centralnabanka.types.Mt102;
import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;

public interface MessageSenderService {

    void sendMessagesToDebtor(Mt102 message);
    void sendMessagesToDebtor(Mt103 message);

    Mt900 createMt900(Mt102 message);
    Mt900 createMt900(Mt103 message);
}
