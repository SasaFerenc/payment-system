package com.centralnabanka.service.implementation;

import com.centralnabanka.service.MessageSenderService;
import com.centralnabanka.types.Mt102;
import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.ObjectFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class MessageSenderServiceImpl extends WebServiceGatewaySupport implements MessageSenderService {


    @Override
    public void sendMessagesToDebtor(Mt102 message) {

    }

    @Override
    public void sendMessagesToDebtor(Mt103 message) {

    }

    @Override
    public Mt900 createMt900(Mt102 message) {
        return null;
    }

    @Override
    public Mt900 createMt900(Mt103 message) {
        Mt900 response = new ObjectFactory().createMt900();

        response.setIdPoruke(message.getIdPoruke());
        response.setSwiftKodDuznika(message.getSwiftKodDuznika());
        response.setObracunskiRacunDuznika(message.getObracunskiRacunDuznika());
        response.setIdPorukeNaloga("MT103-" + message.getIdPoruke());
        response.setDatumValute(message.getPodaciOPlacanju().getDatumValute());
        response.setIznos(message.getPodaciOPlacanju().getIznos());
        response.setSifraValute(message.getSifraValute());

        return  response;
    }
}
