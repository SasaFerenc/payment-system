package com.centralnabanka.service.implementation;

import com.centralnabanka.service.RtgsService;
import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.ObjectFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class RtgsServiceImpl extends WebServiceGatewaySupport implements RtgsService {

    @Override
    public Mt900 processMt103Request(Mt103 request) {

        ObjectFactory factory = new ObjectFactory();

        Mt900 response = factory.createMt900();
        response.setIdPoruke("Test");

        return response;
    }
}
